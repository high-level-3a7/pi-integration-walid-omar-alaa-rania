/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_materiel;

import jdk.nashorn.internal.runtime.Source;
import models.Etat;
import models.Materiel;
import models.categorie;
import service.ServiceCategorie;
import service.ServiceMateriel;
import service.ServiceEtat;

/**
 *
 * @author walid
 */
public class Gestion_materiel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource cnx = new DataSource();
        ServiceCategorie sc = new ServiceCategorie();
        ServiceMateriel sm1 = new ServiceMateriel();
        ServiceEtat se = new ServiceEtat();
       // sm.ajouter(new Materiel("aa", "ee", 5, "zz", 17, 2) );
     // sc.ajouter(new categorie("d", "des", "typ"));
        // categorie ct =new categorie("rr", "aa", "ee");
       // se.modifier(new Etat(1, "vert", "sehihaaa"));
      //  se.afficher().forEach(System.out::println);
   //   se.supprimer(new Etat(1) );
  // se.ajouter(new Etat("vert", "sehiha"));
  //sm.ajouter(new Materiel("rr","tt", 2, "image",17, 2));
       //categorie ct1 = new categorie(16, "ww", "ww", "ww");
        //  sc.modifier(16, "aa", "aa", "aa");
        //  sc.modifier(ct1);
//        sc.ajouter(new categorie("Ahmed", "Mahmoud","aa"));
//        sc.ajouter(new categorie("rr","aa","ee"));
//        sc.supprimer(ct);
          //se.ajouter(new Etat("vert","sehiha"));
        // sc.modifier(ct1);
//        sc.afficher().forEach(System.out::println);
        //  sc.afficher().forEach(System.out::println);
        //  ServiceMateriel sm2 = new ServiceMateriel();
        // Materiel m1 = new Materiel(17, "ww", "ww", 5, "ww",16);
        //sm2.ajouter(new Materiel("zz", "aa", 5, "rr",16));
        //  sm.modifier(m1);
        //sm.supprimer(m1);
        // sm.afficher().forEach(System.out::println);
 //sm.Search("21").forEach(System.out::print);
       //int nbr_m = sm.nbdeMarque("2");
     //  System.out.println(nbr_m);
   //int nbrtot_m = sm.nbParEtat("vert");
     //System.out.println(nbrtot_m);
  //double stat = sm.statMaterilParEtat("oranger");
   //System.out.println(stat);
// sm.ajouter(new Materiel("tet", "aa", 7, "image",18, 2, "oranger"));
//sm.ajouter(new Materiel("hh", "rr", 6, "image", 18, "oranger"));
    // se.ajouter(new Etat("oranger", "mesta3mel"));
    // sm.supprimer(new Materiel(27));
 //  sm.afficher().forEach(System.out::println);
 //-------------------------------------------
       // sc.ajouter(new categorie("musclation", "ttt", "sport individuelle"));
       //  sc.ajouter(new categorie("aerobic", "eee", "sport collectif"));
        // sc.ajouter(new categorie("box", "sss", "sport collectif"));
       //  sc.supprimer(new categorie(26));
        //sc.modifier(new categorie(18,"nation", "fzzf", "individuelle"));
      //  sc.afficher().forEach(System.out::println);
      
      
      //se.ajouter(new Etat("vert", "neuf"));
   //  se.ajouter(new Etat("oranger", "occasion"));
  //   se.ajouter(new Etat("rouge", "défectueuse"));
  //   se.supprimer(new Etat(13));
  //   se.modifier(new Etat(11, "type_etat", "description")); 
  //   se.afficher().forEach(System.out::println);
  
// sm.ajouter(new Materiel("gonds", "yy", 5, "ee", 29,14));
  //sm.ajouter(new Materiel("byclette", "rockrider", 10, "image",22, "type_etat"));
 // sm.ajouter(new Materiel("Sac de frappe", "marque", 3, "image",23, "oranger"));
 // sm.ajouter(new Materiel("tapis", "yamaha",10, "im", 21, "oranger"));
  //  sm.modifier(new Materiel(25, "nom", "marque", 0, "image", 23, "type_etat"));
    //sm.supprimer(new Materiel(25));
   // sm1.Search("f").forEach(System.out::println);
   //int nbrtot_m = sm1.nbParEtat("ff");
  // System.out.println(nbrtot_m);
   // String stat = sm1.statTerrain("15");
  //  double stat = sm1.statMaterilParEtat("17");
     // System.out.println(stat);
     //int x=  sc.getIdbyNom();
    //  System.out.println(x);
    //  int y= se.getIdbyEtat("vert");
//   //         System.out.println("musculation : "+x);
      //   System.out.println("vert : "+y);
    // sm1.modifier(new Materiel(77, "nommm", "marqyyue", 45, 40, 24));
   // Materiel mat = new Materiel();
   
        sm1.afficher().forEach(System.out::println);
    }
    
}
