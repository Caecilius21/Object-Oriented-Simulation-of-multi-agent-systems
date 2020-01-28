package conway;

public class Grid {
    private int gridSize;
    private EtatConway grilleT_0[][];
    private EtatConway grilleT_1[][];
    private RulesConway rules;

    public Grid(int gridSize, RulesConway rule) {
        this.gridSize = gridSize;
        this.grilleT_0 = new EtatConway[gridSize][gridSize];
        this.grilleT_1 = new EtatConway[gridSize][gridSize];
        this.rules = rules;

        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                this.grilleT_0[i][j] = EtatConway.MORT;
                this.grilleT_1[i][j] = EtatConway.MORT;
            }
        }

        this.makeSpaceShip();
    }

    private void makeSpaceShip() {
        this.grilleT_0[0][0] = EtatConway.VIVANT;
        this.grilleT_0[0][1] = EtatConway.VIVANT;
        this.grilleT_0[0][2] = EtatConway.VIVANT;
        this.grilleT_0[1][0] = EtatConway.VIVANT;
        this.grilleT_0[2][1] = EtatConway.VIVANT;
    }

    /**
     * Calcule la grille d'instant T_1 à partir de T_0
     */
    public void next() {
        EtatConway voisins[];
        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                voisins = this.getVoisins(i, j);
                this.grilleT_1[i][j] = this.evaluer(this.grilleT_0[i][j], this.nbVivant(voisins));
            }
        }

        // T_1 devient T_0
        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                voisins = this.getVoisins(i, j);
                this.grilleT_0[i][j] = this.grilleT_1[i][j];
            }
        }
    }

    private EtatConway evaluer(EtatConway etatCourant, int nbVivant) {
        switch (nbVivant) {
            case 3:
                return EtatConway.VIVANT;
            case 2:
                return etatCourant;
            default:
                return EtatConway.MORT;
        }
    }


    private int nbVivant(EtatConway[] voisins) {
        int count = 0;
        for (EtatConway e: voisins) {
            if (e.equals(EtatConway.VIVANT)) {
                count++;
            }
        }

        return count;
    }


    private EtatConway[] getVoisins(int i, int j) {
        /**
         * jm1 == 'j moins 1' == j-1
         * jp1 == 'j plus 1' == j+1
         */
        int im1 = i - 1;
        int ip1 = i + 1;
        int jm1 = j - 1;
        int jp1 = j + 1;

        if (i == 0) {
            im1 = this.gridSize - 1;
        } else if (i == this.gridSize - 1) {
            ip1 = 0;
        }

        if (j == 0) {
            jm1 = this.gridSize - 1;
        } else if (j == this.gridSize - 1) {
            jp1 = 0;
        }

        EtatConway[] voisins = {
                this.grilleT_0[im1][jm1],
                this.grilleT_0[im1][j],
                this.grilleT_0[im1][jp1],
                this.grilleT_0[i][jm1],
                this.grilleT_0[i][jp1],
                this.grilleT_0[ip1][jm1],
                this.grilleT_0[ip1][j],
                this.grilleT_0[ip1][jp1]
        };


        return voisins;
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                str += this.grilleT_0[i][j] + " ";
            }
            str += "\n";
        }

        return str;
    }

    public EtatConway getCell(int i, int j) {
        return this.grilleT_0[i][j];
    }
}
