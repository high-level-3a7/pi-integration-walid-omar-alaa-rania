/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rania
 */
public class Emploi {
    private int IdEmploi ;
    private int IdSeance;
    private int idzone;
    private String DateE;
    
    
    
    public Emploi(int IdEmploi) {
    this.IdEmploi= IdEmploi;
    }
    public Emploi(int IdEmploi,String DateE) {
    this.IdEmploi= IdEmploi;
    this.DateE=DateE;
    }
    public Emploi(int IdEmploi,int IdSeance,int idzone,String DateE ){
    this.IdEmploi=IdEmploi;
    this.IdSeance=IdSeance;
    this.idzone=idzone;
  
    this.DateE=DateE;
    }
    
    public Emploi(int IdSeance,int idzone,String DateE){
    this.IdSeance=IdSeance;
    this.idzone=idzone;
    
    this.DateE=DateE;
    }

    
    
    public int getIdEmploi(){
    return IdEmploi;
    }
    public void setIdEmploi(int IdEmploi) {
        this.IdEmploi = IdEmploi;
    }
    
    
    public int getIdSeance(){
    return IdSeance;
    }
    public void setIdSeance(int IdSeance) {
        this.IdSeance = IdSeance;
    }
    
    
    
    
    
    public String getDateE() {
        return DateE;
    }
    public void setDateE(String DateE) {
        this.DateE = DateE;
    }

    @Override
    public String toString() {
        return "Emploi{" + "IdEmploi=" + IdEmploi + ", IdSeance=" + IdSeance + ", idzone=" + idzone + ", DateE=" + DateE + '}';
    }

    public int getIdzone() {
        return idzone;
    }

    public void setIdzone(int idzone) {
        this.idzone = idzone;
    }
    
    
    

    
    
    
    
    
    
    
}
