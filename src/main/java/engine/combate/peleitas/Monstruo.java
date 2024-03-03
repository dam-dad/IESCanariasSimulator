package engine.combate.peleitas;

import javafx.scene.image.Image;

import java.util.Random;

public class Monstruo {
    private String name;
    private int money;
    private Image enemy_image;
    private String[] skill = new String[2];

    private double vida;
    private double vida_maxima;
    private double ataque;
    private double defensa;
    private double ataque_magico;
    private double defensa_magica;

    private double velocidad;

    //constructor
    public Monstruo() {
        this.name = generateRandomMonsterName();
        initializeStatsBasedOnName();
    }

    private String generateRandomMonsterName() {
        String[] possibleNames = {"Monstruo1", "Monstruo2", "Monstruo3"};
        Random random = new Random();
        return possibleNames[random.nextInt(possibleNames.length)];
    }

    private void initializeStatsBasedOnName() {
        // Asocia las estadísticas según el nombre del monstruo
        if ("Monstruo1".equals(name)) {
            this.money = 20;
            this.enemy_image = new Image("/spider_border.png");
            this.vida = 50;
            this.vida_maxima = vida;
            this.ataque = 5;
            this.defensa = 5;
            this.ataque_magico = 2;
            this.defensa_magica = 2;
            this.skill[0] = "Fire rain";
            this.skill[1] = "Lava rain";
            this.velocidad = 10;




        } else if ("Monstruo2".equals(name)) {
            this.money = 30;
            this.enemy_image = new Image("/dog_outline.png");
            this.vida = 80;
            this.vida_maxima = vida;
            this.ataque = 2;
            this.defensa = 2;
            this.ataque_magico = 5;
            this.defensa_magica = 5;
            this.skill[0] = "Waterfall";
            this.skill[1] = "Waterstorm";
            this.velocidad = 15;

        } else if ("Monstruo3".equals(name)) {
            this.money = 25;
            this.enemy_image = new Image("/minotaur-outlined.png");
            this.vida = 120;
            this.vida_maxima = vida;
            this.ataque = 5;
            this.defensa = 5;
            this.ataque_magico = 5;
            this.defensa_magica = 5;
            this.skill[0] = "Tornado";
            this.skill[1] = "Mega Tornado";
            this.velocidad = 25;

        }


    }

    //getters

    public String getName() {
        return name;
    }

    public int getMoney(){return money;}

    public Image getEnemy_image(){return enemy_image;}

    public double getAtaque() {return ataque;}

    public double getAtaque_magico() {return ataque_magico;}

    public double getDefensa() {return defensa;}

    public double getDefensa_magica() {return defensa_magica;}

    public double getVida() {return vida;}

    public void calcularVida(int vidaQuitada) {this.vida -= vidaQuitada;}

    public double getVida_maxima() {return vida_maxima;}

    public String[] getSkill() {return skill;}

    public double getVelocidad() {return velocidad;}

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setEnemy_image(Image enemy_image) {
        this.enemy_image = enemy_image;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void setVida_maxima(double vida_maxima) {
        this.vida_maxima = vida_maxima;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public void setAtaque_magico(double ataque_magico) {
        this.ataque_magico = ataque_magico;
    }

    public void setDefensa_magica(double defensa_magica) {
        this.defensa_magica = defensa_magica;
    }

    public void setVelocidad(double velocidad) {this.velocidad = velocidad;}

    //Acciones

    public int damageFisico (Jugador jugador){
        double ataque = this.ataque;
        double defensaJugador = jugador.getDefensa();
        double vidaJugador = jugador.getVida();

        int fallo = new java.util.Random().nextInt(99) + 1;
        if (fallo > 90) return 0;

        double damage = Math.max(0, ataque - defensaJugador) + new Random().nextInt(5);

        if (vidaJugador - damage <= 0){
            damage = vidaJugador;
        }

        return (int) damage;

    }

    public int damageSkill (Jugador jugador, int skill){
        double ataqueMagico = this.ataque_magico;
        double defensaMagicaJugador = jugador.getDefensa_magica();
        double vidaJugador = jugador.getVida();

        double damageExtra = 1.0;

        int fallo = new java.util.Random().nextInt(99) + 1;
        if (fallo > 90) return 0;

        if (skill == 1) {
            damageExtra = 2.00;
        }

        double damage = Math.max(0, ataqueMagico * damageExtra - defensaMagicaJugador) + new Random().nextInt(5);
        //si la vida restante del jugador menos el daño hecho por el monstruo es igual o menor a 0, el daño sera igual a la vida restante del jugador.
        if (vidaJugador - damage <= 0){
            damage = vidaJugador;
        }

        return (int) damage;
    }

    public boolean Muerto() {
        return this.vida <= 0;
    }

    public int iaAccion(Jugador jugador) {
        int ia = new java.util.Random().nextInt(9) + 1;
        int ia2 = new java.util.Random().nextInt(9) + 1;
        double damage;
        if (ia < 5) {
            damage = damageFisico(jugador);}
        else if (ia2 < 5){
            damage = damageSkill(jugador, 1);}
        else {damage = damageSkill(jugador, 2);}

        return (int) damage;
    }
}
