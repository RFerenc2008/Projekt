import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

    public class Main {
        public static void main(String[] args) {
            Scanner scanner = null;

            ArrayList<Jatekos> jatekosok = new ArrayList<>();

            try {
                scanner = new Scanner(new File("src/jatekosok.txt"));

                // Fejlécsor átugrása
                if (scanner.hasNext()) {
                    scanner.nextLine();
                }

                while (scanner.hasNext()) {
                    String sor = scanner.nextLine();
                    if (sor.isEmpty()) continue;

                    String[] adatok = sor.split(";");
                    if (adatok.length == 4) {
                        String nev = adatok[0];
                        String csapat = adatok[1];
                        int golok = Integer.parseInt(adatok[2]);
                        int eletkor = Integer.parseInt(adatok[3]);

                        jatekosok.add(new Jatekos(nev, csapat, golok, eletkor));
                    }
                }

                System.out.println("Beolvasott játékosok száma: " + jatekosok.size());

                Scanner konzol = new Scanner(System.in);
                boolean fut = true;

                while (fut) {
                    System.out.println("\n 2026-os aranylabda jelöltek");
                    System.out.println("1. Összes játékos kiírása");
                    System.out.println("2. Rendezés gólszám szerint csökkenőbe");
                    System.out.println("3. Összes szerzett gól száma");
                    System.out.println("4. Gólkirály megkeresése");
                    System.out.println("5. Szűrés csapat alapján");
                    System.out.println("6. Keresés név alapján");
                    System.out.println("7. Kilépés");
                    System.out.print("Választás: ");

                    String valasztas = konzol.nextLine();

                    if (valasztas.equals("1")) {
                        // 1. Összes játékos (Stream forEach)
                        jatekosok.stream()
                                .forEach(System.out::println);

                    } else if (valasztas.equals("2")) {
                        // 2. Rendezés gólszám szerint csökkenőbe (Stream sorted)
                        jatekosok.stream()
                                .sorted(Comparator.comparingInt(Jatekos::getGolok).reversed())
                                .forEach(System.out::println);

                    } else if (valasztas.equals("3")) {
                        // 3. Összes szerzett gól száma (Stream mapToInt + sum)
                        int osszesGol = jatekosok.stream()
                                .mapToInt(Jatekos::getGolok)
                                .sum();
                        System.out.println("Az összes szerzett gól: " + osszesGol);

                    } else if (valasztas.equals("4")) {
                        // 4. Gólkirály megkeresése (Stream max)
                        Optional<Jatekos> max = jatekosok.stream()
                                .max(Comparator.comparingInt(Jatekos::getGolok));

                        if (max.isPresent()) {
                            System.out.println("Gólkirály: " + max.get());
                        } else {
                            System.out.println("Nincs adat!");
                        }

                    } else if (valasztas.equals("5")) {
                        // 5. Szűrés csapat alapján (Stream filter)
                        System.out.print("Csapat neve: ");
                        String csapat = konzol.nextLine().toLowerCase();

                        jatekosok.stream()
                                .filter(j -> j.getCsapat().toLowerCase().contains(csapat))
                                .forEach(System.out::println);

                    } else if (valasztas.equals("6")) {
                        // 6. Keresés név alapján (Stream filter)
                        System.out.print("Keresett név: ");
                        String nev = konzol.nextLine().toLowerCase();

                        jatekosok.stream()
                                .filter(j -> j.getNev().toLowerCase().contains(nev))
                                .forEach(System.out::println);

                    } else if (valasztas.equals("7")) {
                        System.out.println("Kilépés...");
                        fut = false;
                    } else {
                        System.out.println("Érvénytelen menüpont!");
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (scanner != null) scanner.close();
            }
        }
    }
