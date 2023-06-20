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


//        long readCount = context.getStepContext().getStepExecution().getReadCount();
//        long writeCount = context.getStepContext().getStepExecution().getWriteCount();
        Integer chunk = context.getStepContext().getStepExecution().getVersion();
//        AnsiConsole.out().println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Chunk: " + ((chunk - 1)) + " concluído"));
        AnsiConsole.out().install();
        AnsiConsole.out.print("\r" + Ansi.ansi().eraseLine().a("Chunk número: " + ((chunk - 1)) + " concluído").reset());
        AnsiConsole.out.flush();
//        System.out.print("Chunk: " + ((chunk - 1)) + " concluído");
//        System.out.println();
//        System.out.flush();
    }
}
