    package br.senac.sp.gamesfx.model;

    public class Estudio {
        private int id;
        private String nomeFundador;
        private int anoFundacao;
        private String paisOrigem;
        private String nomeEstudio;


        public Estudio() {
        }

        public Estudio(int id,
                       String nomeEstudio,
                       String nomeFundador,
                       int anoFundacao,
                       String paisOrigem) {

            this.id = id;
            this.nomeEstudio = nomeEstudio;
            this.nomeFundador = nomeFundador;
            this.anoFundacao = anoFundacao;
            this.paisOrigem = paisOrigem;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNomeEstudio() {
            return nomeEstudio;
        }

        public String getNomeFundador() {
            return nomeFundador;
        }

        public void setNomeFundador(String nomeFundador) {
            this.nomeFundador = nomeFundador;
        }


        public int getAnoFundacao() {
            return anoFundacao;
        }

        public void setAnoFundacao(int anoFundacao) {
            this.anoFundacao = anoFundacao;
        }

        public String getPaisOrigem() {
            return paisOrigem;
        }

        public void setPaisOrigem(String paisOrigem) {
            this.paisOrigem = paisOrigem;
        }

        @Override
        public String toString() {
            return this.nomeEstudio;
        }

        public void setNomeEstudio(String nomeEstudio) {
            this.nomeEstudio = nomeEstudio;
        }

    }
