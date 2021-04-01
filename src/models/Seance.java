/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import services.ServiceSeance;
/**
 *
 * @author rania
 */
public class Seance {
    
    ServiceSeance sts = new ServiceSeance();
    private int IdSeance;
    private int Capacite;
    private int IdCoach;
    private String DateS;
    private int activiteid;
    private String nom;
    

    

    public Seance(int Capacite, int IdCoach, String DateS) {
         this.Capacite= Capacite;
    this.IdCoach =IdCoach;
    this.DateS= DateS;
    }

   

    public Seance(int IdSeance, int Capacite, int IdCoach, String DateS) {
       this.IdSeance= IdSeance;
    this.Capacite= Capacite;
    this.IdCoach =IdCoach;
    this.DateS= DateS;
    }
    
     
    
    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }
   
    
    public Seance(int IdSeance) {
    this.IdSeance= IdSeance;
    }
    
    
    public Seance() {
    
    }
    public Seance(int IdSeance,int Capacite,int IdCoach,String DateS,int activiteid){
    this.IdSeance= IdSeance;
    this.Capacite= Capacite;
    this.IdCoach =IdCoach;
    this.DateS= DateS;
    this.activiteid=activiteid;
    this.nom =sts.getnombyactiviteid(activiteid);
   
    
    
    }
    
    
    public Seance(int Capacite,int IdCoach,String DateS,int activiteid){
    this.Capacite= Capacite;
    this.IdCoach =IdCoach;
    this.DateS= DateS;
    this.activiteid=activiteid;
    }
    
    public int getIdSeance(){
    return IdSeance;
    }
    public void setIdSeance(int IdSeance) {
        this.IdSeance = IdSeance;
    }
    
    
    public int getCapacite(){
    return Capacite;
    }
    public void setCapacite(int Capacite) {
        this.Capacite = Capacite;
    }
    
    
    public int getIdCoach(){
    return IdCoach;
    }
    public void setIdCoach(int IdCoach) {
        this.IdCoach = IdCoach;
    }
    
    
    public String getDateS() {
        return DateS;
    }
    public void setDateS(String DateS) {
        this.DateS = DateS;
    }

    

    public ServiceSeance getSts() {
        return sts;
    }

    public void setSts(ServiceSeance sts) {
        this.sts = sts;
    }

    public int getActiviteid() {
        return activiteid;
    }

    public void setActiviteid(int activiteid) {
        this.activiteid = activiteid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return "Seance{" + "IdSeance=" + IdSeance + ", Capacite=" + Capacite + ", IdCoach=" + IdCoach + ", DateS=" + DateS + ", activiteid=" + activiteid + ", nom=" + nom +  '}';
    }
    
    
    
}
