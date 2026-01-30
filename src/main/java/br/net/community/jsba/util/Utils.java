package br.net.community.jsba.util;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

/**
 * Classe: Utils
 *
 * <p>Classe de utilidades.<br>
 *  - Identificar tipo de informação: e-mail, telefone, cpf ou cnpj<br>
 *  - Formatar Data<br>
 *  - Formatar cpf ou cnpj<br>
 *  - Retirar caracters especiais de uma string</p>
 *
 * Responsável: Alexandre José da Rocha<br>
 * Desde: 2026-01-28
 */

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Regex para email
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // Regex para telefone simples (com ou sem DDD, aceita 8 ou 9 dígitos)
    private static final Pattern TELEFONE_REGEX =
            Pattern.compile("^(\\+\\d{1,3})?\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}\\-?\\d{4}$");

    // Regex para CPF (somente formato, não valida dígitos verificadores)
    private static final Pattern CPF_REGEX =
            Pattern.compile("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$");

    // Regex para CNPJ (somente formato, não valida dígitos verificadores)
    private static final Pattern CNPJ_REGEX =
            Pattern.compile("^\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}$");

    public static final String FORMATO_CPF = "###.###.###-##";
    public static final String FORMATO_CNPJ = "##.###.###/####-##";

    /**
     * Retorna o tipo de dado informado: EMAIL, TELEFONE, CPF, CNPJ ou DESCONHECIDO
     */
    public static String identificarTipo(String valor) {
        if (valor == null || valor.isBlank()) {
            return "DESCONHECIDO";
        }

        valor = valor.trim();

        if (EMAIL_REGEX.matcher(valor).matches()) {
            return "EMAIL";
        } else if (TELEFONE_REGEX.matcher(valor).matches()) {
            return "TELEFONE";
        } else if (CPF_REGEX.matcher(valor).matches()) {
            return "CPF";
        } else if (CNPJ_REGEX.matcher(valor).matches()) {
            return "CNPJ";
        }

        return "DESCONHECIDO";
    }

    public static String formatarData(LocalDateTime data, String... formato) {
        String pattern = formato.length > 0 ? formato[0] : "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        
        if (Objects.isNull(data)) {
            return null;
        }
        
        return data.format(formatter);
    }

    public static String formatarCpfCnpj(String cpfCnpj) {
        String retorno = cpfCnpj.replaceAll("\\D", "");
        
        if (retorno.isEmpty()) {
            return null;
        }

        String mascara = "";
        
        if (retorno.length() <= 11) {
            mascara = FORMATO_CPF;
        } else if (retorno.length() <= 14) {
            mascara = FORMATO_CNPJ;
        }

        try {
            retorno = StringUtils.leftPad(retorno, StringUtils.countMatches(mascara, "#"), "0");
            MaskFormatter mask = new MaskFormatter(mascara);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(retorno);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String removerCaracteresEspeciais(String texto) {
        return texto.replaceAll("[^a-zA-Z0-9]", "");
    }
}
