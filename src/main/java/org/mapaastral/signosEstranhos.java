package org.mapaastral;

import org.mapaastral.exception.SignoLunarNaoEncontradoException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class signosEstranhos {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        System.out.println("##################################################");
        System.out.println("############# Mapa astral - Bank S.A #############");
        System.out.println("##################################################");
        System.out.println("###### Diz aí, quando vc nasceu? ");
        System.out.print("dia (ex.: 3 ou 31): ");
        int dia = input.nextInt();
        System.out.print("mês (ex.: 1 ou 12): ");
        int mes = input.nextInt();
        System.out.print("ano (ex.: 1998): ");
        int ano = input.nextInt();
        System.out.print("hora (ex.: 1 ou 23): ");
        int hora = input.nextInt();
        System.out.print("minuto (ex.: 1 ou 60): ");
        int minuto = input.nextInt();
        System.out.println("####### Muito bom! Só mais uma coisa, qual cidade vc nasceu? (não precisa colocar acentos)");
        System.out.print("cidade (ex.: Sao Paulo): ");
        String cidade = input.next();
        System.out.println();
        System.out.println("##################################################");
        System.out.println("##################################################");

        LocalDateTime dataHoraNascimento = LocalDateTime.of(ano, mes, dia, hora, minuto);
        ZoneId zoneId = ZoneId.of("America/" + formatarStringDeZoneId(cidade));
        ZonedDateTime dataHoraLocalNascimento = ZonedDateTime.of(dataHoraNascimento, zoneId);

        System.out.print("Sua idade: ");
        Period period = Period.between(dataHoraLocalNascimento.toLocalDate(), LocalDate.now());
        System.out.println(period.getYears() + " anos");

        System.out.print("Data de nascimento formatada: ");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dateTimeFormatter.format(dataHoraNascimento);
        System.out.println(dataFormatada);

        System.out.print("Fuso horário do seu local de nascimento: ");
        System.out.println(zoneId.getRules().getOffset(dataHoraNascimento));

        System.out.print("Seu signo: ");
        System.out.println(Signos.getSigno(MonthDay.of(dataHoraLocalNascimento.getMonth(), dataHoraLocalNascimento.getDayOfMonth())));

        System.out.print("E o ascendente: ");
        System.out.println(Ascendente.getAscendente(dataHoraLocalNascimento.toLocalTime()));

        System.out.print("Signo Lunar: ");
        try {
            System.out.println(Lunar.getLunar(dataHoraLocalNascimento));
        } catch (SignoLunarNaoEncontradoException exception) {
            System.out.println(exception.getLocalizedMessage());
        }

        System.out.println(Year.isLeap(dataHoraLocalNascimento.getYear()) ?
                "Você nasceu " : "Você não nasceu " + "em ano bixesto");
    }

    private static String formatarStringDeZoneId(String cidade) {
        char[] arr = cidade.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr).replaceAll(" ", "_");
    }
}