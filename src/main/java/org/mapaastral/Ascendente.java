package org.mapaastral;

import org.mapaastral.exception.SignoNaoEncontradoException;

import java.time.LocalTime;
import java.util.Arrays;

public enum Ascendente {
    ARIES(LocalTime.of(6, 31), LocalTime.of(8, 30)),
    TOURO(LocalTime.of(8, 31), LocalTime.of(10, 30)),
    GEMEOS(LocalTime.of(10, 31), LocalTime.of(12, 30)),
    CANCER(LocalTime.of(12, 31), LocalTime.of(14, 30)),
    LEAO(LocalTime.of(14, 31), LocalTime.of(16, 30)),
    VIRGEM(LocalTime.of(16, 31), LocalTime.of(18, 30)),
    LIBRA(LocalTime.of(18, 31), LocalTime.of(20, 30)),
    ESCORPIAO(LocalTime.of(20, 31), LocalTime.of(22, 30)),
    SAGITARIO(LocalTime.of(22, 31), LocalTime.of(0, 30)),
    CAPRICORNIO(LocalTime.of(0, 31), LocalTime.of(2, 30)),
    AQUARIO(LocalTime.of(2, 31), LocalTime.of(4, 30)),
    PEIXES(LocalTime.of(4, 31), LocalTime.of(6, 30));

    private final LocalTime dataInicial;
    private final LocalTime dataFinal;

    Ascendente(LocalTime dataInicial, LocalTime dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public LocalTime getDataInicial() {
        return dataInicial;
    }

    public LocalTime getDataFinal() {
        return dataFinal;
    }

    public static Ascendente getAscendente(LocalTime anivesario) {
        return Arrays.stream(Ascendente.values()).filter(ascendente -> {
            if (ascendente.equals(Ascendente.SAGITARIO)) {
                return (!anivesario.isBefore(ascendente.getDataInicial()) || !anivesario.isAfter(ascendente.getDataFinal()));
            } else {
                return !(anivesario.isBefore(ascendente.getDataInicial()) || anivesario.isAfter(ascendente.getDataFinal()));
            }
        }).findFirst().orElseThrow(SignoNaoEncontradoException::new);
    }
}