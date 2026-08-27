package com.example.bookstore.services;


import com.example.bookstore.models.GraphicCard;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TecnologyService {


    private final ChatClient chatClient;

    public TecnologyService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }


    public void findByCard(GraphicCard data){
        try{
            var prompt= """
                    Realizar busca de placa de vídeo modelo %s.
                    Marca da placa %s.
                    Ano de fabricação %s.
                    Não repetir a informação no texto, informar o valor médio do produto da busca.
                    E caso possível, informar modelos de opções semelhantes levando em consideração a memória de vídeo e o preço do produto, o preço da sugestão podendo flutuar entre R$500,00 acima ou abaixo da média da busca.
                    Apresentar as informações em jormato JSON separado, formatado da forma a seguir.
                    'nome_modelo' sendo o modelo da placa gráfica pesquisada,'descricao' onde pode ser uma informacao resumida da arquitetura do produto pesquisado limitado a 100 caracteres ,  'preco' o valor médio da placa pesquisada resultado apenas numericos não precisa apresentar o tipo da moeda, 'marca' o tipo de fábricante da placa pesquisada, 'sugestao' placa similar ao da busca, 'ano' de fabricação
                    """.formatted(data.getModelo(), data.getMarca(), data.getAnoModelo());


            var busca = chatClient.prompt().user(prompt).call().content();

            if(busca == null || busca.isEmpty()){
                throw new Exception("Busca não encontrada!");
            }

            String formatedJson = busca.substring(busca.indexOf("{"), busca.lastIndexOf("}")+1);

            ObjectMapper oMapper = new ObjectMapper();
            JsonNode jNode = oMapper.readTree(formatedJson);


                data.setSugestao("Opções Semelhantes: "+jNode.get("sugestao").asText());
                data.setDescricao(jNode.get("descricao").asText());
                data.setPreco(BigDecimal.valueOf(jNode.get("preco").asDouble()));



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
