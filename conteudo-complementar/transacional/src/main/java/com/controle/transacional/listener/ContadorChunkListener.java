package com.controle.transacional.listener;

import lombok.SneakyThrows;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class ContadorChunkListener implements ChunkListener {

    @SneakyThrows
    @Override
    public void beforeChunk(ChunkContext context) {

        ChunkListener.super.afterChunk(context);

        long chunk = context.getStepContext().getStepExecution().getCommitCount();
        AnsiConsole.out().install();

        AnsiConsole.out.print("\r" + Ansi.ansi().eraseLine().a("Número de chunks concluídos " + geraSeta(chunk) + "\t" +(chunk)).reset());
        AnsiConsole.out.flush();


        AnsiConsole.out().uninstall();
    }

    private String geraSeta(Long numero) {

        // Reduzindo o número para o intervalo de 1 a 10
        long resultado = ((numero - 1) % 10) + 1;

        StringBuilder seta = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            if (i == resultado) {
                seta.append("->");
            } else {
                seta.append(" ");
            }
        }
        return seta.toString();
    }
}
