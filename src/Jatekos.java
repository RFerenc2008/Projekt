public class Jatekos {
        private String nev;
        private String csapat;
        private int golok;
        private int eletkor;

        public Jatekos(String nev, String csapat, int golok, int eletkor) {
            this.nev = nev;
            this.csapat = csapat;
            this.golok = golok;
            this.eletkor = eletkor;
        }

        public String getNev() {
            return nev;
        }

        public String getCsapat() {
            return csapat;
        }

        public int getGolok() {
            return golok;
        }

        public int getEletkor() {
            return eletkor;
        }

        @Override
        public String toString() {
            return nev + " (" + csapat + ") - " + golok + " gól, " + eletkor + " éves";
        }
    }

