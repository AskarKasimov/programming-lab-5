public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MusicBand musicBand = new MusicBand();
            System.out.println(musicBand.getId());
        }
        main2();
        MusicBand musicBand = new MusicBand();
        System.out.println(musicBand.getId());
    }

    public static void main2() {
        for (int i = 0; i < 10; i++) {
            MusicBand musicBand = new MusicBand();
            System.out.println(musicBand.getId());
        }
        MusicBand musicBand = new MusicBand();
        System.out.println(musicBand.getId());
    }
}