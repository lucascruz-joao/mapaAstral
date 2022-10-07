package org.mapaastral;

import org.mapaastral.exception.SignoLunarNaoEncontradoException;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public enum Lunar {
    CASIMIRO(ZoneId.of("America/Recife"), LocalTime.of(12, 1), LocalTime.of(23, 59)),
    ODIN(ZoneId.of("America/Cuiaba"), LocalTime.of(0, 0), LocalTime.of(12, 0)),
    GANDALF(ZoneId.of("America/Sao_Paulo"), LocalTime.of(0, 0), LocalTime.of(23, 59));

    private final ZoneId zoneId;
    private final LocalTime dataInicial;
    private final LocalTime dataFinal;

    Lunar(ZoneId zoneId, LocalTime dataInicial, LocalTime dataFinal) {
        this.zoneId = zoneId;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public LocalTime getDataInicial() {
        return dataInicial;
    }

    public LocalTime getDataFinal() {
        return dataFinal;
    }

    public static Lunar getLunar(ZonedDateTime zonedDateTime) {
        ZoneId nascimentoZoneId = zonedDateTime.getZone();
        LocalTime nascimentoLocalTime = zonedDateTime.toLocalTime();

        for (Lunar lunar : Lunar.values()) {
            if (nascimentoZoneId.equals(lunar.getZoneId())) {
                if (!((nascimentoLocalTime.isAfter(lunar.getDataFinal())) || nascimentoLocalTime.isBefore(lunar.getDataInicial()))) {
                    return lunar;
                }
            }
        }

        throw new SignoLunarNaoEncontradoException("Em construção");
    }

}
