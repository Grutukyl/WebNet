package webnet;

import java.io.Serializable;

public class Document implements Serializable {
    public String nome = "Vaticano e suas ordens reais.";

    public String getNome() {
        return nome;
    }

    public byte[] getNomeByte(){
        return nome.getBytes();
    }
}
