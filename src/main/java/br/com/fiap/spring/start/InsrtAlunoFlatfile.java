package br.com.fiap.spring.start;

import br.com.fiap.spring.model.Aluno;
import br.com.fiap.spring.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.fiap.spring.utils.MessageConstants.*;

@Component
public class InsrtAlunoFlatfile implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InsrtAlunoFlatfile.class);

    @Value("${file.path.txt}")
    private Resource resource;

    @Autowired
    private AlunoRepository repository;

    private final int ARRAY_SIZE = 25;
    private final int NOME_INDEX_START = 0;
    private final int NOME_INDEX_END = 41;
    private final int RM_INDEX_START = 41;
    private final int RM_INDEX_END = 48;
    private final int COD_INDEX_START = 49;
    private final int COD_INDEX_END = 55;
    
    private final Pattern REGEX_NOME = Pattern.compile("^[a-zA-Z]");
    
    @Override
    public void run(ApplicationArguments args) {
        if(resource.exists()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
                List<Aluno> alunoList = new ArrayList();
                String line = "";

                while((line = reader.readLine()) != null){
                    Matcher matcher = REGEX_NOME.matcher(line);

                    if(matcher.find()){
                        Aluno aluno = new Aluno();
                        aluno.setNome(line.substring(NOME_INDEX_START, NOME_INDEX_END).toUpperCase().trim());
                        aluno.setRm(line.substring(RM_INDEX_START, RM_INDEX_END).trim());
                        aluno.setCod(line.substring(COD_INDEX_START, COD_INDEX_END).trim());
                        alunoList.add(aluno);

                        if(alunoList.size() == ARRAY_SIZE){


                            // TODO Nao esta salvando a lista inteira. Verificar!!!!


                            repository.saveAll(alunoList);

                            logger.info(TITULO_LISTA_ALUNOS_INSERIDOS);
                            alunoList.forEach(n -> logger.info(n.getNome()));

                            alunoList.clear();
                        }
                    }
                }

                deleteFiles();

            } catch(IOException ioException){
                logger.info(ERRO_PROCESSAR_ARQUIVO_ALUNOS);
            }
        } else {
            logger.info(NAO_HA_NOVA_LISTA_ALUNOS);
        }

    }

    private void deleteFiles() throws  IOException {
        File file = resource.getFile();
        String absoluteFilePath = resource.getFile().getAbsolutePath().replace("target/classes","src/main/resources");
        File absoluteFile = new File(absoluteFilePath);

        file.delete();
        absoluteFile.delete();
    }
}
