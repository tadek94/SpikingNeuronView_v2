package tk;

import javafx.scene.control.ToggleGroup;

import java.util.Random;

/**
 * Created by Tadzik on 2016-05-30.
 */
public class Los {
    int[] tabDoLosowaniaCzesto = new int[10];
    int[] tabDoLosowaniaNormalnie = new int[10];
    int[] tabDoLosowaniaRzadko = new int[10];

    float[] waga = new float[4];
    float sumaWagMembrana = 1;

    int[] tablicaImpulsów = new int[4];

    public int[] getTabDoLosowaniaCzesto() {
        return tabDoLosowaniaCzesto;
    }

    public void setTabDoLosowaniaCzesto(int[] tabDoLosowaniaCzesto) {
        this.tabDoLosowaniaCzesto = tabDoLosowaniaCzesto;
    }

    public int[] getTabDoLosowaniaNormalnie() {
        return tabDoLosowaniaNormalnie;
    }

    public void setTabDoLosowaniaNormalnie(int[] tabDoLosowaniaNormalnie) {
        this.tabDoLosowaniaNormalnie = tabDoLosowaniaNormalnie;
    }

    public float[] getWaga() {
        return waga;
    }

    public void setWaga(float[] waga) {
        this.waga = waga;
    }

    public float getSumaWagMembrana() {
        return sumaWagMembrana;
    }

    public void setSumaWagMembrana(float sumaWagMembrana) {
        this.sumaWagMembrana = sumaWagMembrana;
    }



    public int getNextValue() {
        Random rand = new Random();
        return rand.nextInt(10);
    }
    public int getWartImpulsuRzadko() {
        tabDoLosowaniaRzadko[0] =1;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =1;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =0;
        tabDoLosowaniaRzadko[0] =1;


        return tabDoLosowaniaRzadko[getNextValue()];
    }

    public int getWartImpulsuNorm() {
        tabDoLosowaniaNormalnie[0] = 0;
        tabDoLosowaniaNormalnie[1] = 1;
        tabDoLosowaniaNormalnie[2] = 0;
        tabDoLosowaniaNormalnie[3] = 1;
        tabDoLosowaniaNormalnie[4] = 0;
        tabDoLosowaniaNormalnie[5] = 1;
        tabDoLosowaniaNormalnie[6] = 0;
        tabDoLosowaniaNormalnie[7] = 1;
        tabDoLosowaniaNormalnie[8] = 0;
        tabDoLosowaniaNormalnie[9] = 1;

        return tabDoLosowaniaNormalnie[getNextValue()];
    }

    public int getWartImpulsuCzesto() {
        tabDoLosowaniaCzesto[0] = 0;
        tabDoLosowaniaCzesto[1] = 1;
        tabDoLosowaniaCzesto[2] = 1;
        tabDoLosowaniaCzesto[3] = 1;
        tabDoLosowaniaCzesto[4] = 0;
        tabDoLosowaniaCzesto[5] = 1;
        tabDoLosowaniaCzesto[6] = 1;
        tabDoLosowaniaCzesto[7] = 1;
        tabDoLosowaniaCzesto[8] = 0;
        tabDoLosowaniaCzesto[9] = 0;


        return tabDoLosowaniaCzesto[getNextValue()];

    }

    public float getNextWag() {
        Random rand = new Random();
        return rand.nextFloat();
    }

    public float[] losujTabWag()
    {
        for(int i=0; i<4; i++)
        {
            waga[i] = getNextWag();
        }
        return waga;
    }



    public float sumaNaMembranie(float tab[])
    {
        float wynik=0;

        for (float x:tab) {

            wynik +=x;

        }
        sumaWagMembrana = wynik;

        return sumaWagMembrana;
    }

}
