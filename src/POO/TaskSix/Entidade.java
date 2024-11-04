public abstract class Entidade {
    public static final String ATIVO = "Ativo";
    public static final String INATIVO = "Inativo";
    public static final String PENDENTE = "Pendente";

    private int id;
    private String status;

    public Entidade(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void exibirDetalhes();
}
