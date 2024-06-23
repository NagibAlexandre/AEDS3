import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import aed3.Cripto;
import aed3.Registro;

public class Categoria implements Registro {
    private final String CHAVE = "ANACANOANA";
    private int ID;
    private String nome;
    private String descricao;

    public Categoria() {
        this(-1, "", "");
    }

    public Categoria(int id, String nome, String descricao) {
        this.ID = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream ba_out = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(ba_out);
        dos.writeInt(this.ID);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.descricao);
        return Cripto.cod(ba_out.toByteArray(), CHAVE);
    }

    public void fromByteArray(byte[] ba) throws Exception {
        ba = Cripto.decod(ba, this.CHAVE);
        ByteArrayInputStream ba_in = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(ba_in);
        this.ID = dis.readInt();
        this.nome = dis.readUTF();
        this.descricao = dis.readUTF();
    }

    public String toString() {
        return "ID: " + this.ID +
                "\nNome: " + this.nome +
                "\nDescrição: " + this.descricao;
    }

    public int compareTo(Object b) {
        return this.getID() - ((Categoria) b).getID();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
