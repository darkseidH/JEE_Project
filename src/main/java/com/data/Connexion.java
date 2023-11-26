//package com.data;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.*;
//import java.time.LocalDate;
//
//
//public class Connexion {
//    static Connection myCnx ;
//    String url,pilote;
//    public static String ipAdresse = null,port=null,NomDatabase=null,password=null,utilisateur=null;
//    public Connexion() {
//          try {
//          //Chargement de pilote
//          pilote=new String("com.mysql.cj.jdbc.Driver");
//          Class.forName(pilote);
//          // Definition de l'URL de connexion
//          url = new String("jdbc:mysql://"+ipAdresse+":"+port+"/"+NomDatabase+"?serverTimezone=UTC");
//          }catch( ClassNotFoundException e) {
//            System.err.println("Erreur lors du chargement du pilote : " + e);
//          }
//        }
//
//    public void Connection(){
//        try{
//         // Etablissement de la Connexion
//        myCnx = DriverManager.getConnection(url,utilisateur,password);
//        System.out.println("CONNEXION ETABLIE");
//        }catch (SQLException e) {
//        System.err.println("Erreur de syntaxe SQL :" + e);
//        }
//    }
//
//    public void Deconnection(){
//        try{
//            // Fermeture de la connexion
//            myCnx.close();
//            System.out.println("DECONNEXION");
//        }catch (SQLException e) {
//            System.err.println("Erreur de syntaxe SQL :" + e);
//            }
//       }
//
//       public static void AjouterUtilisateurBD( Utilisateur utilisateur){
//        if(utilisateur.getImage() != null){
//            try {
//                // String queryInsert="INSERT INTO utilisateur VALUES("+"'"+utilisateur.getEmail()+"','"+utilisateur.getLogin()+"','"+utilisateur.getNom()+"','"+utilisateur.getNumeroTelephone()+"','"+utilisateur.getPassword()+"','"+utilisateur.getType()+"',null,?)";
//                String queryInsert="INSERT INTO utilisateur VALUES("+"'"+utilisateur.getEmail()+"',"+utilisateur.getEtat()+",?,'"+utilisateur.getLogin()+"','"+utilisateur.getNom()+"','"+utilisateur.getNumeroTelephone()+"','"+utilisateur.getPassword()+"','"+utilisateur.getType()+"',null)";
//                try (PreparedStatement statement = Connexion.myCnx.prepareStatement(queryInsert)) {
//                    InputStream inputStream = new FileInputStream(utilisateur.getImage());
//                    statement.setBinaryStream(1, inputStream);
//                    statement.executeUpdate();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            } catch (SQLException e) {
//
//            }
//        }else{
//            Statement statement;
//            try {
//                statement = myCnx.createStatement();
//                String queryInsert="INSERT INTO utilisateur VALUES("+"'"+utilisateur.getEmail()+"',"+utilisateur.getEtat()+",null,'"+utilisateur.getLogin()+"','"+utilisateur.getNom()+"','"+utilisateur.getNumeroTelephone()+"','"+utilisateur.getPassword()+"','"+utilisateur.getType()+"',null)";
//                statement.executeUpdate(queryInsert);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        }
//       public static void AjouterPatientBD( Patient patient){
//            Statement statement;
//            try {
//                statement = myCnx.createStatement();
//                String queryInsert="INSERT INTO patient VALUES('"+patient.getAdresse()+"',"+patient.getAgeMere()+","+patient.getAgePere()+",'"+patient.getAsurence()+"','"+patient.getDateNaissance()+"','"+patient.getEmail()+"',"+patient.getIp()+",'"+patient.getIpPatient()+"',"+patient.getIpFamille()+",'"+patient.getNom()+"','"+patient.getNumeroTelephone()+"','"+patient.getOrigine()+"','"+patient.getProfessionMere()+"','"+patient.getProfessionPere()+"',"+patient.isScolarise()+",'"+patient.getSexe()+"',null,0,"+patient.getIpCHU()+",'"+patient.getLieunaissance()+"')";
//                statement.executeUpdate(queryInsert);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    public static String getIpPatient(int ipFamille) throws SQLException{
//        ResultSet res=null;
//		String req="SELECT getIpPatientFromDB("+ipFamille+")";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//            res.next();
//            return res.getString(1);
//    }
//
//    public static ResultSet getAllData(String table) {
//		ResultSet res=null;
//		String req="SELECT * FROM "+table;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//	}
//    public static ResultSet getNomCetegorie(String id) {
//		ResultSet res=null;
//		String req="SELECT Nom FROM categorie WHERE CategorieID = "+id+" LIMIT 1";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//	}
//
//    public static ResultSet get_IDs_Indication_DeType(String idTypeAnalyseChoisi) {
//        ResultSet res=null;
//		String req="SELECT IndicationbdID FROM typeanalyseindication WHERE TypeanalysebdID = "+idTypeAnalyseChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNomIndication(String id) {
//        ResultSet res=null;
//		String req="SELECT Nom FROM indicationbd WHERE IndicationbdID = "+id+" LIMIT 1";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet get_IDs_Abre_TypeAnalyse_DeCategorie(String idCategorieChoisi) {
//        ResultSet res=null;
//		String req="SELECT TypeAnalysebdID ,Abreviation FROM TypeAnalysebd WHERE CategorieID = "+idCategorieChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdsProfessionnelleSantesFromResponsable(String idIndicationChoisi) {
//        ResultSet res=null;
//		String req="SELECT ProfessionnellesanteID ,Responsable FROM responsable WHERE IndicationbdID = "+idIndicationChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInfoUtilisateurRealisateur(String idProfessionnelle) {
//        ResultSet res=null;
//		String req="SELECT Nom  FROM utilisateur WHERE UtilisateurID = "+idProfessionnelle;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdsProfessionnelleSantesFromProffe() {
//        ResultSet res=null;
//		String req="SELECT * FROM professionnellesante ";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdMedecinFromCat(String idCategorieChoisi) {
//        ResultSet res=null;
//		String req="SELECT MedecinID FROM categorie where CategorieID = "+idCategorieChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInformationPersonnellesPatient(String idDossierMedicale) {
//        ResultSet res=null;
//		String req="SELECT * FROM patient where PatientID = "+idDossierMedicale;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInfoUtilisateurSonImage() {
//        ResultSet res=null;
//		String req="SELECT Nom,Email,Login,Password,Type,UtilisateurID,Numerotelephone FROM utilisateur";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdDossierMedicale(String idPatient) {
//        ResultSet res=null;
//		String req="SELECT DossierMedicaleID FROM Dossiermedicale where PatientID = "+idPatient;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateATCDPersonnelle(ATCDPersonnelle atcdPersonnelle,String DossiermedicaleID) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            // String queryUpdate="UPDATE Atcdpersonnelle SET Allaitement = "+atcdPersonnelle.getAllaitement() +" , SET Allergies =  "+atcdPersonnelle.getAllergies() +" , SET Autre =  "+atcdPersonnelle.getAutre() +" , SET Autreatcdmch =  "+atcdPersonnelle.getAutreATCDMCH() +" , SET Courbescroissance =  "+atcdPersonnelle.getCourbesCroissance() +" , SET Developpementpsychomoteur =  "+atcdPersonnelle.getDeveloppementPsychomoteur() +" , SET Diversificationalimentaire =  "+atcdPersonnelle.getDiversificationAlimentaire() +" , SET Grosseseetatcommentaire =  "+atcdPersonnelle.getGrosseseEtatCommentaire() +" , SET Grossesseetat =  "+atcdPersonnelle.getGrossesseEtat() +" , SET Grossessesuivrecommentaire =  "+atcdPersonnelle.getGrossesseSuivreCommentaire() +" , SET Grossessesuivreetat =  "+atcdPersonnelle.getGrossesseSuivreEtat() +" , SET Modeaccouchement =  "+atcdPersonnelle.getModeAccouchement() +" , SET Poidsnassance =  "+atcdPersonnelle.getPoidsNassance() +" , SET Souffrance =  "+atcdPersonnelle.getSouffrance() +" , SET Vaccination =  "+atcdPersonnelle.getVaccination() +" WHERE DossiermedicaleID =  "+DossiermedicaleID;
//            String queryUpdate = "UPDATE Atcdpersonnelle SET Allaitement = '" + atcdPersonnelle.getAllaitement() +
//    "', Allergies = '" + atcdPersonnelle.getAllergies() +
//    "', Autre = '" + atcdPersonnelle.getAutre() +
//    "', Autreatcdmch = '" + atcdPersonnelle.getAutreATCDMCH() +
//    "', Courbescroissance = '" + atcdPersonnelle.getCourbesCroissance() +
//    "', Developpementpsychomoteur = '" + atcdPersonnelle.getDeveloppementPsychomoteur() +
//    "', Diversificationalimentaire = '" + atcdPersonnelle.getDiversificationAlimentaire() +
//    "', Grosseseetatcommentaire = '" + atcdPersonnelle.getGrosseseEtatCommentaire() +
//    "', Grossesseetat = '" + atcdPersonnelle.getGrossesseEtat() +
//    "', Grossessesuivrecommentaire = '" + atcdPersonnelle.getGrossesseSuivreCommentaire() +
//    "', Grossessesuivreetat = '" + atcdPersonnelle.getGrossesseSuivreEtat() +
//    "', Modeaccouchement = '" + atcdPersonnelle.getModeAccouchement() +
//    "', Poidsnassance = '" + atcdPersonnelle.getPoidsNassance() +
//    "', Souffrance = '" + atcdPersonnelle.getSouffrance() +
//    "', Vaccination = '" + atcdPersonnelle.getVaccination() +
//    "' WHERE DossiermedicaleID = " + DossiermedicaleID;
//
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateExamenParacllinique(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Dossiermedicale SET Examenparaclinique = '"+text+"' WHERE DossiermedicaleID = " +text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateHestoireMaladie(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Dossiermedicale SET Histoiremaladie = '"+text+"' WHERE DossiermedicaleID = " +text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateMiseAjour(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Dossiermedicale SET MiseAjour = '"+text+"' WHERE DossiermedicaleID = " +text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateMotifConsultation(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Dossiermedicale SET Motifconsultation = '"+text+"' WHERE DossiermedicaleID = " +text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateDiagnostique(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Dossiermedicale SET Diagnostic = '"+text+"' WHERE DossiermedicaleID = " +text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getInfoDossierMedicale(String text) {
//        ResultSet res=null;
//		String req="SELECT * FROM Dossiermedicale where DossiermedicaleID = "+text;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdATCDF(String text) {
//        ResultSet res=null;
//		String req="SELECT AtcdfamiliauxID FROM atcdfamiliaux where DossiermedicaleID = "+text;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateDecesFamille(DecesDansFamille decesDansFamille, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE decesdansfamille SET Age = '" + decesDansFamille.getAge()+
//    "', Causedeces = '" + decesDansFamille.getCauseDeces() +
//    "', Nom = '" + decesDansFamille.getNom() +
//    "' WHERE DecesdansfamilleID = " + string;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void InsertDecesFamille(DecesDansFamille decesDansFamille) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  decesdansfamille VALUES ('" + decesDansFamille.getAge()+
//    "','" + decesDansFamille.getCauseDeces() +
//    "','" + decesDansFamille.getNom() +
//    "',null,"+ decesDansFamille.getATCDFID()+")";
//         statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateATCDF(ATCDFamiliaux atcdFamiliaux) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE Atcdfamiliaux SET Autre = '" + atcdFamiliaux.getAutre()+
//    "', Consanguinte = " + atcdFamiliaux.getConsanguinte() +
//    ", ConsanguinteDegre = '" + atcdFamiliaux.getConsanguinteDegre() +
//    "', Enfantsatteints = '" + atcdFamiliaux.getEnfantsAtteints() +
//    "', Freressoeursatteints = '" + atcdFamiliaux.getFreresSoeursAtteints()+
//    "', Gdmerematernel = " + atcdFamiliaux.getGdMereMaternel() +
//    ", Gdmerematernelcommentaire = '" + atcdFamiliaux.getGdMereMaternelCommentaire() +
//    "', Gdmerepaternel = " + atcdFamiliaux.getGdMerePaternel() +
//    ", Gdmerepaternelcommentaire = '" + atcdFamiliaux.getGdMerePaternelCommentaire() +
//    "', Gdperematernel = " + atcdFamiliaux.getGdPereMaternel() +
//    ", Gdperematernelcommentaire = '" + atcdFamiliaux.getGdPereMaternelCommentaire() +
//    "', Gdperepaternel = " + atcdFamiliaux.getGdPerePaternel() +
//    ", Gdperepaternelcommentaire = '" + atcdFamiliaux.getGdPerePaternelCommentaire() +
//    "', Mereatteints = " + atcdFamiliaux.getMereAtteints() +
//    ", Mereatteintscommantaire = '" +atcdFamiliaux.getMereAtteintsCommantaire() +
//    "', Pereatteints = " + atcdFamiliaux.getPereAtteints() +
//    ", Pereatteintscommentaire = '" + atcdFamiliaux.getPereAtteintsCommentaire() +
//    "' WHERE DossiermedicaleID = " + atcdFamiliaux.getDossierMedicaleID();
//
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getATCDPfromDB(String idDossierMedicale) {
//        ResultSet res=null;
//		String req="SELECT * FROM Atcdpersonnelle where DossiermedicaleID = "+idDossierMedicale;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getATCDFfromDB(String idDossierMedicale) {
//        ResultSet res=null;
//		String req="SELECT * FROM Atcdfamiliaux where DossiermedicaleID = "+idDossierMedicale;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getAllDecesFamille(String string) {
//        ResultSet res=null;
//		String req="SELECT * FROM decesdansfamille where AtcdfamiliauxID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//
//    public static void AjouterAnalyseMedicaleBD(AnalyseMedicale analyseMedicale) {
//        Statement statement;
//            try {
//                statement = myCnx.createStatement();
//                String queryInsert;
//                if(analyseMedicale.getM_Medecin() ==0)
//                queryInsert ="INSERT INTO Analysemedicale VALUES('"+analyseMedicale.getDateAnalyse()+"',"+analyseMedicale.getDatePrelevment()+",'"+analyseMedicale.getIndication()+"',null,"+analyseMedicale.getM_ProfessionnelleSante()+",null,"+analyseMedicale.getM_Patient()+",'"+analyseMedicale.getId()+"',"+analyseMedicale.getTypeAnalyseMedicale()+",0,"+analyseMedicale.getDelai()+",0)";
//                else
//                queryInsert ="INSERT INTO Analysemedicale VALUES('"+analyseMedicale.getDateAnalyse()+"',"+analyseMedicale.getDatePrelevment()+",'"+analyseMedicale.getIndication()+"',null,"+analyseMedicale.getM_ProfessionnelleSante()+","+analyseMedicale.getM_Medecin()+","+analyseMedicale.getM_Patient()+",'"+analyseMedicale.getId()+"',"+analyseMedicale.getTypeAnalyseMedicale()+",0,"+analyseMedicale.getDelai()+",0)";
//                statement.executeUpdate(queryInsert);
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Demande analyse est ajouter ");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Demande analyse est ajouter avec succes.");
//                    alert.showAndWait();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//    }
//
//    public static ResultSet getTousTypesAnalysesNfaitProSante(int idUtilisateur, String idCategorie) {
//        ResultSet res=null;
//		String req="SELECT distinct a.TypeanalysebdID,t.Abreviation FROM analysemedicale a ,typeanalysebd t ,categorie c where  "+
//        "a.TypeanalysebdID = t.TypeanalysebdID and t.CategorieID = c.CategorieID and c.CategorieID = "+idCategorie +
//        " and a.ProfessionnellesanteID = "+idUtilisateur +
//        " and a.fait =0 and a.Dateprelevment is not null";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getTousAnalysesNfaitTProSante(int idUtilisateur, String string) {
//        ResultSet res=null;
//		String req="SELECT * FROM analysemedicale where TypeanalysebdID = "+string+" and fait = 0 and Dateprelevment is not null and ProfessionnellesanteID = "+idUtilisateur ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getEtapsAnalyse(String idDemandeAnalyseDB) {
//        ResultSet res=null;
//		String req="SELECT * FROM suiveetapsanalyse where AnalysemedicaleID = "+idDemandeAnalyseDB+" order by Numero";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNomEtapByID(String string) {
//        ResultSet res=null;
//		String req="SELECT Nom FROM etapanalyse where EtapanalyseID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getEtapAsuivieAnalyse(String idDemandeAnalyseDB) {
//        ResultSet res=null;
//		String req="SELECT SuiveetapsanalyseID FROM suiveetapsanalyse where AnalysemedicaleID = "+idDemandeAnalyseDB+" and Daterealisation is null limit 1 ";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdDernierLigne(String text, String text2) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  moduleresultatbd VALUES ('"+text+"','"+text2+"',null )" ;
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX( ModuleresultatbdID) from moduleresultatbd";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void ajouterDetailleModuleResultat(String text, String text2, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  detaillemoduleresultatbd VALUES ('"+text+"','"+text2+"',"+string+",null)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getModileResultatByID(String string) {
//        ResultSet res=null;
//		String req="SELECT * FROM detaillemoduleresultatbd where  ModuleresultatbdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdDernierLigneFromMR(String text, String text2) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  moduleresultat VALUES ('"+text+"',null,"+text2+")" ;
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX( ModuleresultatID) from moduleresultat";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void ajouterDetailleModuleResultatAM(String text, String text2, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  detaillemoduleresultat VALUES ('"+text+"','"+text2+"',"+string+",null)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void validerEtapAnalyseMSE(String text) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE suiveetapsanalyse SET Daterealisation = '"+LocalDate.now()+"' WHERE  SuiveetapsanalyseID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getIdAnalyseMFMR(String idDemandeAnalyseDB) {
//        ResultSet res=null;
//		String req="SELECT  ModuleresultatID FROM moduleresultat where AnalysemedicaleID = "+idDemandeAnalyseDB;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void validerEtapAnalyseMAE(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE suiveetapsanalyse SET Daterealisation = '"+LocalDate.now()+"' , Exigencecommentaire = '"+text2+"' WHERE  SuiveetapsanalyseID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getDelaiAnalyse(String typeAnalyseMedicale) {
//        ResultSet res=null;
//		String req="SELECT  Delai FROM typeanalysebd where typeanalysebdID = "+typeAnalyseMedicale;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInfoAnalyseAsuive(String idDemandeAnalyseDB) {
//        ResultSet res=null;
//		String req="SELECT Delai, Dateprelevment,ProfessionnellesanteID,Nombrecheque FROM analysemedicale where  AnalysemedicaleID = "+idDemandeAnalyseDB;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//
//    public static void updateNbrEchequeDelai1(String text, String delai) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE analysemedicale SET Delai = "+delai +",Nombrecheque = 1  WHERE  analysemedicaleID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getTypeAnalyseMedicale(String text) {
//        ResultSet res=null;
//		String req="SELECT t.* FROM analysemedicale a ,Typeanalysebd t where a.AnalysemedicaleID = "+text+" and a.TypeanalysebdID = t.TypeanalysebdID";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void AjouterNotification1(String text, String realisateur, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  notifications VALUES ("+text+","+realisateur+",'"+string+"',1,0,0,0,0,0)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void updateNbrEchequeDelai2(String text, String delaiString) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE analysemedicale SET Delai = "+delaiString +",Nombrecheque = 2  WHERE  analysemedicaleID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void AjouterNotification2(String text, String realisateur, String typeAnalyse) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  notifications VALUES ("+text+","+realisateur+",'"+typeAnalyse+"',2,0,1,0,0,0)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void RefaireAnalyseMedicale(String text, String delai1) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate1 = "UPDATE suiveetapsanalyse SET Daterealisation = null  where  AnalysemedicaleID = " +text;
//            String queryUpdate2 = "UPDATE analysemedicale SET Delai = "+delai1 +" where  AnalysemedicaleID = " +text;
//            statement.executeUpdate(queryUpdate1);
//            statement.executeUpdate(queryUpdate2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void SupprimerMR(String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert1 = "DELETE from detaillemoduleresultat where  ModuleresultatID = "+string;
//            String queryInsert2 = "DELETE from moduleresultat where  ModuleresultatID = "+string;
//            statement.executeUpdate(queryInsert1);
//            statement.executeUpdate(queryInsert2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void AjouterNotification3(String text, String realisateur, String typeAnalyse) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  notifications VALUES ("+text+","+realisateur+",'"+typeAnalyse+"',0,0,1,0,1,0)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getAnalysesSansPrelevement() {
//        ResultSet res=null;
//		String req="SELECT  AnalysemedicaleID,PatientID,TypeanalysebdID from analysemedicale where Dateprelevment is null";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void validerReceptionPrelevement(String text) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE analysemedicale SET Dateprelevment = '"+LocalDate.now() +"' WHERE  analysemedicaleID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNotificationsNA(int idUtilisateur) {
//        ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Nombreecheque = 0 and Administrareurconcerne =0 and SecraitaireConcerne = 0 and Consulterparps=0 and ProfessionnellesanteID = "+idUtilisateur;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNomPatient(String string) {
//        ResultSet res=null;
//		String req="SELECT p.Nom from patient p,notifications n , analysemedicale a where a.AnalysemedicaleID = n.AnalysemedicaleID and p.PatientID = a.PatientID and n.AnalysemedicaleID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void SupprimerNotificationsNA(int idUtilisateur) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "DELETE FROM notifications where Nombreecheque = 0 and Administrareurconcerne =0 and SecraitaireConcerne = 0 and ProfessionnellesanteID = "+idUtilisateur;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateNotificationsNA(int idUtilisateur) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparps = 1  where   Administrareurconcerne =0 and SecraitaireConcerne = 0 and Nombreecheque = 0 and Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	public static ResultSet getNotificationsNbechec_1(int idUtilisateur) {
//		ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Nombreecheque = 1 and Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//	}
//
//    public static void UpdateNotificationsNbechec_1(int idUtilisateur) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparps = 1  where Nombreecheque = 1 and Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	public static ResultSet getNotificationsNbechec_2(int idUtilisateur) {
//		ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Nombreecheque = 2 and Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//	}
//
//    public static void UpdateNotificationsNbechec_2(int idUtilisateur) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparps = 1  where Nombreecheque = 2 and Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNotificationsEchecRPProfS(int idUtilisateur) {
//        ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Administrareurconcerne = 1 and SecraitaireConcerne = 1 and  Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateNotificationsEchecRPProfS(int idUtilisateur) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparps = 1 where Administrareurconcerne =1 and SecraitaireConcerne = 1 and  Consulterparps = 0 and ProfessionnellesanteID = "+idUtilisateur;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNotificationsEchecRPAdmin() {
//        ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Administrareurconcerne = 1 and SecraitaireConcerne = 1 and  Consulterparad = 0 ";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateNotificationsEchecRPAdmin() {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparad = 1 where Administrareurconcerne =1 and SecraitaireConcerne = 1 and  Consulterparad = 0 ";
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNotificationsNbechec_2Admin() {
//        ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Nombreecheque = 2 and Consulterparad = 0 ";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateNotificationsNbechec_2Admin() {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET Consulterparad = 1  where Nombreecheque = 2 and Consulterparad = 0 ";
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateNotificationsEchecRPSecretaire() {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE notifications SET consulterparec = 1 where Administrareurconcerne =1 and SecraitaireConcerne = 1 and  consulterparec = 0 ";
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNotificationsEchecRPSecretaire() {
//        ResultSet res=null;
//		String req="SELECT AnalysemedicaleID, typeAnalyse from notifications where Administrareurconcerne = 1 and SecraitaireConcerne = 1 and  consulterparec = 0 ";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void supprimerNotificationsConsulter() {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate1 = "DELETE FROM notifications where Nombreecheque = 0  and Administrareurconcerne =0 and SecraitaireConcerne = 0 and Consulterparps = 1 ";
//            String queryUpdate2 = "DELETE FROM notifications where Nombreecheque = 2  and Consulterparad = 1 and Consulterparps = 1 and SecraitaireConcerne = 0 ";
//            String queryUpdate3 = "DELETE FROM notifications where  Consulterparad = 1 and consulterparec = 1 and Consulterparps = 1 and SecraitaireConcerne = 1";
//            statement.executeUpdate(queryUpdate1);
//            statement.executeUpdate(queryUpdate2);
//            statement.executeUpdate(queryUpdate3);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getLastIdPS() {
//        ResultSet res=null;
//		String req="SELECT MAX(professionnellesanteID) from professionnellesante";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdTypeAnalyse_Indication(String string) {
//        ResultSet res=null;
//		String req="SELECT TypeanalysebdID from typeanalyseindication where IndicationbdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//
//    public static ResultSet getNomTypeAnalyse(String string) {
//        ResultSet res=null;
//		String req="SELECT Abreviation from typeanalysebd where typeanalysebdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIndicationsTypeNP(String text, String text2) {
//        ResultSet res=null;
//		String req="SELECT IndicationbdID from typeanalyseindication where  TypeanalysebdID = "+text
//        +" and IndicationbdID not in (select distinct IndicationbdID from responsable where ProfessionnellesanteID = "+text2+")";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void InsererOnResponsable(String text, String text2, int i) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  responsable VALUES ("+i+",null,"+text+","+text2+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateResponsable(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE responsable SET Responsable = 0 where IndicationbdID = "+text2 +"  and ProfessionnellesanteID <>  "+text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getIndicationsNP(String text) {
//        ResultSet res=null;
//		String req="SELECT  IndicationbdID from indicationbd where   IndicationbdID  not in (select distinct  IndicationbdID from responsable where ProfessionnellesanteID =  "+text +")";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet gettypeAnalyseCategorie(String text) {
//        ResultSet res=null;
//		String req="SELECT  TypeanalysebdID from typeanalysebd where   CategorieID = "+text ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInfoUtilisateur(int idUtilisateur) {
//        ResultSet res=null;
//		String req="SELECT  * from utilisateur where   UtilisateurID = "+idUtilisateur ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void updateUtilisateur(String text, String text2, String text3, String text4, String text5,File selectedFile,int idUtilisateur) {
//                if(selectedFile != null){
//                    try {
//                        // String queryInsert="INSERT INTO utilisateur VALUES("+"'"+utilisateur.getEmail()+"','"+utilisateur.getLogin()+"','"+utilisateur.getNom()+"','"+utilisateur.getNumeroTelephone()+"','"+utilisateur.getPassword()+"','"+utilisateur.getType()+"',null,?)";
//                        String queryInsert="UPDATE utilisateur SET Email = '"+text+"' , Nom = '"+text2+"' , Numerotelephone = '"+text3+"', Password = '"+text4+"' ,Login = '"+text5+"' , Image = ? where UtilisateurID = "+idUtilisateur;
//                        try (PreparedStatement statement = Connexion.myCnx.prepareStatement(queryInsert)) {
//                            InputStream inputStream = new FileInputStream(selectedFile);
//                            statement.setBinaryStream(1, inputStream);
//                            statement.executeUpdate();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//
//                    } catch (SQLException e) {
//
//                    }
//                }else{
//                    Statement statement;
//                    try {
//                        statement = myCnx.createStatement();
//                        String queryInsert="UPDATE utilisateur SET Email = '"+text+"' , Nom = '"+text2+"' , Numerotelephone = '"+text3+"', Password = '"+text4+"' ,Login = '"+text5+"'  where UtilisateurID = "+idUtilisateur;
//                        statement.executeUpdate(queryInsert);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//    }
//
//    public static ResultSet getCategorieUtilisateur(int idUtilisateur) {
//        ResultSet res=null;
//		String req="SELECT distinct c.CategorieID from categorie c , typeanalysebd t,responsable r,typeanalyseindication ti where c.CategorieID=t.CategorieID and t.TypeanalysebdID = ti.TypeanalysebdID and ti.IndicationbdID = r.IndicationbdID and r.ProfessionnellesanteID = "+idUtilisateur ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getTypeAnalyseUtilisateur(int idUtilisateur,String IdCategorie) {
//        ResultSet res=null;
//		String req="SELECT distinct t.TypeanalysebdID from typeanalysebd t,responsable r,typeanalyseindication ti where  t.TypeanalysebdID = ti.TypeanalysebdID and ti.IndicationbdID = r.IndicationbdID and r.ProfessionnellesanteID = "+idUtilisateur +" and t.CategorieID = "+IdCategorie;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIndicationUtilisateur(int idUtilisateur, String string) {
//        ResultSet res=null;
//		String req="SELECT distinct ti.IndicationbdID from responsable r,typeanalyseindication ti where   ti.IndicationbdID = r.IndicationbdID and r.ProfessionnellesanteID = "+idUtilisateur +" and ti.TypeanalysebdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNomCategorieCount_Utilisateur(int idUtilisateur) {
//        ResultSet res=null;
//		String req="SELECT distinct c.* from categorie c , typeanalysebd t , analysemedicale a where c.CategorieID = t.CategorieID and t.TypeanalysebdID = a.TypeanalysebdID and a.Fait = 0 and a.ProfessionnellesanteID = "+idUtilisateur;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNombreAnalysesNFCUtilisateur(int idUtilisateur, String string) {
//        ResultSet res=null;
//		String req="SELECT distinct COUNT(a.AnalysemedicaleID ) from categorie c , typeanalysebd t , analysemedicale a where c.CategorieID = t.CategorieID and t.TypeanalysebdID = a.TypeanalysebdID and a.Fait = 0 and a.ProfessionnellesanteID = "+idUtilisateur+" and c.CategorieID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet AjouterCategorieBD(String text, String pRoulement, String localDate) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            if(!pRoulement.isEmpty())
//            queryInsert = "INSERT INTO  categorie VALUES ('"+text+"',"+pRoulement+",null,'"+localDate+"')";
//            else
//            queryInsert = "INSERT INTO  categorie VALUES ('"+text+"',null,null,null)";
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX(CategorieID) from categorie";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//
//
//    public static void InsererRoulementCategorie(String idCategorie, String text, int i) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  roulementcategorie VALUES ("+idCategorie+","+text+","+i+",null)";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet AjouterTypeAnalyseBD(String text, String text2, String text3, String text4, String text5,String text6, String idCAtegorie) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            queryInsert = "INSERT INTO  typeanalysebd VALUES ("+text3+",'"+text+"','"+text2+"',null,"+idCAtegorie+","+text4+","+text5+","+text6+")";
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX(TypeanalysebdID) from typeanalysebd";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void AjouterEtapATypeAnalyse(int i, String text, String text2, int exigence) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  numeroetap VALUES ("+i+",null,"+text+","+text2+","+exigence+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet AjouterEtapAnalyseBD(String text) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            queryInsert = "INSERT INTO  etapanalyse VALUES ('"+text+"',null)";
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX(etapanalyseID) from etapanalyse";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getOrderDernierEtapAnalyse(String idType) {
//        ResultSet res=null;
//		String req="SELECT MAX(Numero) from numeroetap where TypeanalysebdID = "+idType;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void AjouterIndicationTypeAnalyse(String text, String text2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  typeanalyseIndication VALUES (null,"+text+","+text2+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet AjouterIndicationBD(String text, String idType) {
//        ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            queryInsert = "INSERT INTO  indicationbd  VALUES ('"+text+"',null)";
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX(indicationbdID) from indicationbd";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void InsereResponsable(int i, String text, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  responsable VALUES ("+i+",null,"+text+","+string+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getAllAnalyseMPatient(String idPatient) {
//            ResultSet res=null;
//            String req="SELECT * from analysemedicale where PatientID = "+idPatient;
//            try {
//                Statement st=myCnx.createStatement();
//                res=st.executeQuery(req);
//            } catch (SQLException e) {
//
//            }
//            return res;
//    }
//
//    public static ResultSet getModileResultatAnalyseByID(String text) {
//        ResultSet res=null;
//            String req="SELECT * from moduleresultat where AnalysemedicaleID = "+text;
//            try {
//                Statement st=myCnx.createStatement();
//                res=st.executeQuery(req);
//            } catch (SQLException e) {
//
//            }
//            return res;
//    }
//
//    public static ResultSet getDetailleMRDAnalyse(String string) {
//        ResultSet res=null;
//        String req="SELECT * from detaillemoduleresultat where ModuleresultatID = "+string;
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getNomCategorieDemandeAnalyse(String text) {
//        ResultSet res=null;
//        String req="SELECT c.* from categorie c , typeanalysebd t , analysemedicale a  where a.typeanalysebdID = t.typeanalysebdID and t.CategorieID = c.CategorieID and a.AnalysemedicaleID ="+text;
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getNLOfMedRespo(int idMedecinR) {
//        ResultSet res=null;
//        String req="SELECT count(Responsableconsulation) from medecin where Responsableconsulation <= (Select Responsableconsulation from medecin where MedecinID = "+idMedecinR+" ) and Active = 1 ";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getNombreMedecinActive() {
//           ResultSet res=null;
//        String req="SELECT count(Responsableconsulation) from medecin where Active = 1 ";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getIdMedecinRespon(int id) {
//         ResultSet res=null;
//        String req="SELECT max(a.MedecinID) from (select * from medecin where Active = 1 order by Responsableconsulation limit "+id+" )as a ";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getLastLineHestConsultation() {
//        ResultSet res=null;
//        String req="SELECT * from HestoriqueConsultation order by DateResponsabilite Desc limit 1";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getFirstMedecin() {
//        ResultSet res=null;
//        String req="SELECT * from medecin where Active = 1 order by Responsableconsulation  limit 1";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static void InsertIntoHestoriqueConsultation(int idMedecinR, LocalDate plusDays) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  hestoriqueconsultation VALUES ("+idMedecinR+",'"+plusDays+"')";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getMedecinResponsableSemaine(LocalDate date) {
//        ResultSet res=null;
//        String req="SELECT * from hestoriqueconsultation where DateResponsabilite = '"+date+"'";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getCountTypeConsultationDate(String string, LocalDate date) {
//         ResultSet res=null;
//        String req="SELECT count(*) from rendezvousconsultaion where Date = '"+date+"' and Typeconsultation = '"+string+"'";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//	public static ResultSet getRendezVousTypeDate(String typeConsultation, LocalDate date) {
//	    ResultSet res=null;
//        String req="SELECT * from rendezvousconsultaion where Date = '"+date+"' and Typeconsultation = '"+typeConsultation+"'";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//	}
//
//    public static ResultSet getRDVPatient(String text) {
//               ResultSet res=null;
//        String req="SELECT * from rendezvousconsultaion where PatientID = " +text;
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getConsultationUtilsateur(int idUtilisateur,String date ) {
//                      ResultSet res=null;
//        String req="SELECT * from rendezvousconsultaion where Fait = 0 and MedecinID = "+idUtilisateur +" and Date ='"+date+"'";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static ResultSet getConsultationRecherche(int idUtilisateur , String date ) {
//        ResultSet res=null;
//        String req="SELECT p.* , r.* from patient p ,rendezvousconsultaion r where r.Fait = 0 and p.PatientID = r.PatientID and r.MedecinID = "+idUtilisateur +" and r.Date = '"+date+"'";
//        try {
//            Statement st=myCnx.createStatement();
//            res=st.executeQuery(req);
//        } catch (SQLException e) {
//
//        }
//        return res;
//    }
//
//    public static void SetRendezVFait(String text) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE rendezvousconsultaion SET Fait = 1 WHERE  RendezvousconsultaionID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void AjouterRDVConsultationBD(String idpatient, String text, String text2, String text3) {
//         Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  rendezvousconsultaion VALUES ('"+text2+"',0,'"+text3+"',null,"+text+","+idpatient+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getNombreConsultationNFCUtilisateur(int idUtilisateur , String date) {
//         ResultSet res=null;
//		String req="SELECT  COUNT( RendezvousconsultaionID ) from  Rendezvousconsultaion  where  MedecinID = "+idUtilisateur+" and Fait = 0 and Date ='"+date+"'";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getAnalyseMedicale(String text) {
//       ResultSet res=null;
//		String req="SELECT * from analysemedicale where AnalysemedicaleID = "+text;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getDetailleMRDAnalyseImprimer(String text) {
//        ResultSet res=null;
//		String req="SELECT d.Titre , d.Commentaire from detaillemoduleresultat d , moduleresultat m , analysemedicale a where a.AnalysemedicaleID = "+text+" and a.AnalysemedicaleID = m.AnalysemedicaleID and m.ModuleresultatID =  d.ModuleresultatID";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getCountTypeConsultationF(int idUtilisateur, String datedebut, String dateFin,String type,String utilisateurs) {
//        ResultSet res=null;
//        String req;
//        if(utilisateurs.equals("Tous"))
//        req="SELECT  COUNT( RendezvousconsultaionID ) from  Rendezvousconsultaion  where Typeconsultation = '"+ type+"' and Fait = 1 and Date between '"+datedebut +"' and '"+dateFin+"'";
//        else
//		req="SELECT  COUNT( RendezvousconsultaionID ) from  Rendezvousconsultaion  where  MedecinID = "+idUtilisateur+" and Typeconsultation = '"+ type+"' and Fait = 1 and Date between '"+datedebut +"' and '"+dateFin+"'";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getCountTypeConsultationNF(int idUtilisateur, String datedebut, String dateFin,String type ,String utilisateurs) {
//        ResultSet res=null;
//        String req;
//        if(utilisateurs.equals("Tous"))
//         req="SELECT  COUNT( RendezvousconsultaionID ) from  Rendezvousconsultaion  where   Typeconsultation = '"+ type+"' and Fait = 0 and Date between '"+datedebut +"' and '"+dateFin+"'";
//        else
//         req="SELECT  COUNT( RendezvousconsultaionID ) from  Rendezvousconsultaion  where  MedecinID = "+idUtilisateur+" and Typeconsultation = '"+ type+"' and Fait = 0 and Date between '"+datedebut +"' and '"+dateFin+"'";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNombreAnalysesNFUtilisateur(int idUtilisateur , String datedebut , String datefin, String idTRSA, String typeSA) {
//        ResultSet res=null;
//		String req ="";
//        if(!ControllerStatistiques.TypeUS.equals("Tous")){
//        if(typeSA.equals("Tous"))
//        req = "SELECT  COUNT( analysemedicaleID ) from  analysemedicale  where  ProfessionnellesanteID = "+idUtilisateur+"  and fait = 0 and Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Catégorie"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b , categorie c where c.CategorieID = b.CategorieID and b.TypeanalysebdID = a.TypeanalysebdID and c.CategorieID = "+idTRSA+" and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Type d'analyse"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b where b.TypeanalysebdID = a.TypeanalysebdID and b.TypeanalysebdID = "+idTRSA+" and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Indication"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , indicationbd i where i.IndicationbdID = a.IndicationbdID and i.IndicationbdID = "+idTRSA+"and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        }else{
//        if(typeSA.equals("Tous"))
//        req = "SELECT  COUNT( analysemedicaleID ) from  analysemedicale  where  fait = 0 and Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Catégorie"))
//            req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b , categorie c where c.CategorieID = b.CategorieID and b.TypeanalysebdID = a.TypeanalysebdID and c.CategorieID = "+1+" and  a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Type d'analyse"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b where b.TypeanalysebdID = a.TypeanalysebdID and b.TypeanalysebdID = "+idTRSA+" and  a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Indication"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , indicationbd i where i.IndicationbdID = a.IndicationbdID and i.IndicationbdID = "+idTRSA+"and  a.fait = 0 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        }
//
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getNombreAnalysesFUtilisateur(int idUtilisateur , String datedebut , String datefin, String idTRSA, String typeSA) {
//        ResultSet res=null;
//		String req ="";
//        if(!ControllerStatistiques.TypeUS.equals("Tous")){
//        if(typeSA.equals("Tous"))
//        req = "SELECT  COUNT( analysemedicaleID ) from  analysemedicale  where  ProfessionnellesanteID = "+idUtilisateur+"  and fait = 1 and Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Catégorie"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b , categorie c where c.CategorieID = b.CategorieID and b.TypeanalysebdID = a.TypeanalysebdID and c.CategorieID = "+idTRSA+" and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Type d'analyse"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b where b.TypeanalysebdID = a.TypeanalysebdID and b.TypeanalysebdID = "+idTRSA+" and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Indication"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , indicationbd i where i.IndicationbdID = a.IndicationbdID and i.IndicationbdID = "+idTRSA+"and a.ProfessionnellesanteID = "+idUtilisateur+"  and a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        }else{
//        if(typeSA.equals("Tous"))
//        req = "SELECT  COUNT( analysemedicaleID ) from  analysemedicale  where  fait = 1 and Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Catégorie"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b , categorie c where c.CategorieID = b.CategorieID and b.TypeanalysebdID = a.TypeanalysebdID and c.CategorieID = "+idTRSA+" and  a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Type d'analyse"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , typeanalysebd b where b.TypeanalysebdID = a.TypeanalysebdID and b.TypeanalysebdID = "+idTRSA+" and  a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        if(typeSA.equals("Indication"))
//        req = "SELECT  COUNT( a.analysemedicaleID ) from  analysemedicale a , indicationbd i where i.IndicationbdID = a.IndicationbdID and i.IndicationbdID = "+idTRSA+"and  a.fait = 1 and a.Dateanalyse between '"+datedebut +"' and '"+datefin+"'";
//        }
//
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet AjouterExamenCliniqueBD(String text, String text2) {
//         ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            queryInsert = "INSERT INTO  examencliniquebd  VALUES ('"+text2+"','"+text+"',null)";
//            statement.executeUpdate(queryInsert);
//		    String req="SELECT MAX(examencliniquebdID) from examencliniquebd";
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//	public static ResultSet AjouterTObservationBD(String nomTy, String string , String string1) {
//		 ResultSet res=null;
//        Statement statement;
//        try {
//            String queryInsert = null;
//            statement = myCnx.createStatement();
//            queryInsert = "INSERT INTO  typeobservationbd  VALUES ('"+nomTy+"',"+string+",null,"+string1+")";
//            statement.executeUpdate(queryInsert);
//            String req;
//            if(string1 == null)
//		    req="SELECT MAX(TypeobservationbdID) from typeobservationbd where ExamencliniquebdID = "+string;
//            else
//            req="SELECT MAX(TypeobservationbdID) from typeobservationbd where TypeobservationbdPereID = "+string1;
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//	}
//
//    public static ResultSet getObservations(String string) {
//         ResultSet res=null;
//		String req="SELECT * from typeobservationbd where ExamencliniquebdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getTypeObservationEC(String string) {
//        ResultSet res=null;
//		String req="SELECT * from typeobservationbd where TypeobservationbdPereID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getCountChildTOBS(String string) {
//       ResultSet res=null;
//		String req="SELECT COUNT(*) from typeobservationbd where TypeobservationbdPereID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getInfoObesrvation(String string) {
//         ResultSet res=null;
//		String req="SELECT * from typeobservationbd where TypeobservationbdID = "+string;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdEC(String text) {
//         ResultSet res=null;
//		String req="SELECT  ExamencliniquebdID from  examencliniquebd where Nom = '"+text +"'";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getCountIdECFECP(String string, String idDossierM) {
//        ResultSet res=null;
//		String req="SELECT  COUNT(*) from  examenclinique where  ExamencliniqueID = "+string +" and DossiermedicaleID = "+idDossierM ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void deleteExamenCliniquePatient(String idDossierM, String string) {
//         Statement statement;
//         String queryInsert;
//        try {
//            statement = myCnx.createStatement();
//            queryInsert = "DELETE FROM examenclinique where  ExamencliniqueID = "+string + " and  DossiermedicaleID = "+idDossierM;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//      public static void deleteAllObservationPatient(String idDossierM, String string) {
//        Statement statement;
//         String queryInsert;
//        try {
//            statement = myCnx.createStatement();
//            queryInsert = "DELETE FROM typeobservation where  ExamencliniqueID = "+string + " and  DossiermedicaleID = "+idDossierM;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void insertExamneCliniquePatient(String idDossierM, String string,String commentaire) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  examenclinique VALUES ('"+commentaire+"',"+string+","+ idDossierM +")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void insertTypeObservation(String idDossierM, String string, String string2) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "INSERT INTO  typeobservation VALUES ("+string2+","+idDossierM+","+ string+")";
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateExamneCliniquePatient(String idDossierM, String string, String commentaire) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "Update  examenclinique SET Commentaire =  '"+commentaire+"' where   ExamencliniqueID = "+string+" and  DossiermedicaleID = "+ idDossierM;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getCountTOFTOP(String idDossierM, String string, String text) {
//          ResultSet res=null;
//		String req="SELECT  COUNT(*) from  typeobservation where  ExamencliniqueID = "+string + " and DossiermedicaleID = " + idDossierM +" and TypeobservationID ="+text;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void DeleteTypeObservation(String idDossierM, String string, String text) {
//        Statement statement;
//         String queryInsert;
//        try {
//            statement = myCnx.createStatement();
//            queryInsert = "DELETE FROM typeobservation where  ExamencliniqueID = "+string + " and  DossiermedicaleID = "+idDossierM+" and TypeobservationID = "+text;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getExamenCliniquePatient(String text) {
//        ResultSet res=null;
//		String req="SELECT e1.Commentaire , e2.Nom , e1.ExamencliniqueID from  examenclinique e1 ,  examencliniquebd e2  where e1.ExamencliniqueID = e2.ExamencliniquebdID and e1.DossiermedicaleID = "+text;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getExamenCliniqueNPatient(String text) {
//        ResultSet res=null;
//		String req="SELECT * from  examencliniquebd  where ExamencliniquebdID not in (SELECT ExamencliniqueID from  examenclinique where DossiermedicaleID = "+text +")";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getExamenCliniqueFBD(String newValue) {
//        ResultSet res=null;
//		String req="SELECT * from  examencliniquebd  where Nom = '"+newValue+"'" ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getExistantUtilisateur(String password, String text) {
//        ResultSet res=null;
//		String req="SELECT COUNT(*) from  utilisateur  where Login = '"+text+"' and Password = '"+password+"'" ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getPatientsStaffs() {
//          ResultSet res=null;
//		String req="SELECT * from  patient  where Staff = 1";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//    public static ResultSet getPatientsNStaffs() {
//          ResultSet res=null;
//		String req="SELECT * from  patient  where Staff = 0";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void SetStaffYes(String text) {
//         Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE patient SET Staff = 1 WHERE PatientID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void SetStaffNon(String text) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE patient SET Staff = 0 WHERE PatientID = " +text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getCategorieByID(String idCategorieChoisi) {
//          ResultSet res=null;
//		String req="SELECT * from  categorie  where CategorieID = "+idCategorieChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getCountCategorieFromRoulementCategorie(String idCategorieChoisi) {
//             ResultSet res=null;
//		String req="SELECT COUNT(*) from  roulementcategorie  where CategorieID = "+idCategorieChoisi;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIdMedFromRoulementCategorie(int idMed, String idCategorieChoisi) {
//             ResultSet res=null;
//		String req="SELECT * from  roulementcategorie where Orderroulement = "+idMed+" and CategorieID ="+idCategorieChoisi ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getPatientById(String text) {
//             ResultSet res=null;
//		String req="SELECT * from  patient where PatientID =" +text ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdatePatientBD(Patient patient, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "UPDATE patient SET Adresse = '" + patient.getAdresse() +
//                                 "', Agemere = " + patient.getAgeMere() +
//                                 ", Ipchu = " + patient.getIpCHU() +
//                                 ", Agepere = " + patient.getAgePere() +
//                                 ", Asurence = '" + patient.getAsurence() +
//                                 "', Datenaissance = '" + patient.getDateNaissance() +
//                                 "', Email = '" + patient.getEmail() +  // Missing closing single quote here
//                                 "', Nom = '" + patient.getNom() +
//                                 "', Numerotelephone = '" + patient.getNumeroTelephone() +
//                                 "', Origine = '" + patient.getOrigine() +
//                                 "', Professionmere = '" + patient.getProfessionMere() +
//                                 "', Lieunaissance = '" + patient.getLieunaissance() +
//                                 "', ProfessionPere = '" + patient.getProfessionPere() +
//                                 "', Scolarise = " + patient.isScolarise() +
//                                 ", Sexe = '" + patient.getSexe() +
//                                 "' WHERE PatientID = " + string;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void UpdatePatientBD1(Patient patient, String string) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryInsert = "UPDATE patient SET Adresse = '" + patient.getAdresse() +
//                                 "', Agemere = " + patient.getAgeMere() +
//                                 ", Ipchu = " + patient.getIpCHU() +
//                                 ", Agepere = " + patient.getAgePere() +
//                                 ", Asurence = '" + patient.getAsurence() +
//                                 "', Datenaissance = '" + patient.getDateNaissance() +
//                                 "', Email = '" + patient.getEmail() +
//                                 "', Nom = '" + patient.getNom() +
//                                 "', Numerotelephone = '" + patient.getNumeroTelephone() +
//                                 "', Origine = '" + patient.getOrigine() +
//                                 "', Professionmere = '" + patient.getProfessionMere() +
//                                 "', Lieunaissance = '" + patient.getLieunaissance() +
//                                 "', ProfessionPere = '" + patient.getProfessionPere() +
//                                 "', Scolarise = " + patient.isScolarise() +
//                                 ", Ip = " + patient.getIp() +
//                                 ", Ipfamille = " + patient.getIpFamille() +
//                                 ", Ippatient = '" + patient.getIpPatient() +
//                                 "', Sexe = '" + patient.getSexe() +
//                                 "' WHERE PatientID = " + string;
//            statement.executeUpdate(queryInsert);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void ActiveCompteUtilisateur(int idUtil) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE utilisateur SET Etat = 1 WHERE UtilisateurID = " +idUtil;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void DésactiveCompteUtilisateur(int idUtil) {
//         Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE utilisateur SET Etat = 0 WHERE UtilisateurID = " +idUtil;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getMedecin(Object id) {
//          ResultSet res=null;
//		String req="SELECT * from  medecin where MedecinID =" +id ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void ActiveCompteMedisateur(int idUtil) {
//         Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE medecin SET Active = 1 WHERE MedecinID = " +idUtil;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void DésactiveCompteMedisateur(int idUtil) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE medecin SET Active = 0 WHERE MedecinID = " +idUtil;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getInfoCategorie(String selectedOption) {
//         ResultSet res=null;
//		String req="SELECT * from  categorie where Nom = '" +selectedOption +"'" ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getRealisateurCategorie(String string) {
//          ResultSet res=null;
//		String req="SELECT * from  roulementcategorie  where CategorieID = "+string+" order by Orderroulement";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void UpdateCategorie(String text, String pRoulement, String string, String text2) {
//          Statement statement;
//          String queryUpdate;
//        try {
//            statement = myCnx.createStatement();
//            queryUpdate = "DELETE FROM roulementcategorie where CategorieID = "+text2;
//            statement.executeUpdate(queryUpdate);
//            if(!pRoulement.isEmpty())
//            queryUpdate = "UPDATE categorie  SET Periodite = "+pRoulement+" , Nom = '"+text+"'  , DateDebutRoulement = '"+string+"'  WHERE CategorieID = "+text2;
//            else
//            queryUpdate = "UPDATE categorie  SET Periodite = null , Nom = '"+text+"'  , DateDebutRoulement = null  WHERE CategorieID = "+text2;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getInfoTypeAnalyse(String selectedOption) {
//        ResultSet res=null;
//		String req="SELECT * from  typeanalysebd  where Nom = '"+selectedOption+"'";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void ModifierTypeAnalyseBD(String text, String text2, String text3, String text4, String text5,
//            String text6, String text7) {
//                Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "UPDATE typeanalysebd SET Nom = '"+text+"' , Abreviation =  '"+text2+"' , Delai = "+text3+" ,   Delai_1 = "+text4+" , Delai_2 = "+text5+" , DelaiRP = "+text6+" WHERE  TypeanalysebdID = " +text7;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getEtapsTypeAnalyse(String string) {
//        ResultSet res=null;
//		String req="SELECT * from  numeroetap  where TypeanalysebdID = "+string +" order by Numero";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getIndicationsTypeAnalyse(String string) {
//        ResultSet res=null;
//		String req="SELECT * from typeanalyseindication  where typeanalysebdID = "+string ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void DELETEEtapsTypeAnalyse(String text) {
//        Statement statement;
//        try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "DELETE from numeroetap where TypeanalysebdID = "+text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void DELETEIndicationTypeAnalyse(String text) {
//        Statement statement;
//           try {
//            statement = myCnx.createStatement();
//            String queryUpdate = "DELETE from typeanalyseindication where TypeanalysebdID = "+text;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ResultSet getinfoIndication(String selectedOption) {
//        ResultSet res=null;
//		String req="SELECT * from indicationbd  where Nom = '"+selectedOption+"'" ;
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static ResultSet getRealisateurIndication(String string) {
//        ResultSet res=null;
//		String req="SELECT * from responsable where IndicationbdID = "+string +" order by Responsable";
//		try {
//			Statement st=myCnx.createStatement();
//			res=st.executeQuery(req);
//		} catch (SQLException e) {
//
//		}
//		return res;
//    }
//
//    public static void DELETEResponsableIndicationBD(String idIndication, String string) {
//         Statement statement;
//         String queryUpdate;
//           try {
//            statement = myCnx.createStatement();
//            queryUpdate = "Update indicationbd SET Nom = '"+string+"' where IndicationbdID = "+idIndication;
//            statement.executeUpdate(queryUpdate);
//            queryUpdate = "DELETE from responsable where IndicationbdID = "+idIndication;
//            statement.executeUpdate(queryUpdate);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void UpdateArberGDossierMedicale(byte[] fileContent, String text) {
//            String query = "Update dossiermedicale SET Filearbreg = ? where DossiermedicaleID = ? ";
//            try (PreparedStatement statement = myCnx.prepareStatement(query)) {
//                statement.setString(2, text);
//                statement.setBytes(1, fileContent);
//                statement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static byte[] getFileArbreG(String text) {
//            String query = "SELECT Filearbreg FROM dossiermedicale WHERE DossiermedicaleID = ?";
//            try (PreparedStatement statement = myCnx.prepareStatement(query)) {
//                statement.setString(1,text);
//                ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    return resultSet.getBytes("Filearbreg");
//                }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static ResultSet getPatientCategorie(String idCategorie) {
//        ResultSet res=null;
//         String req="SELECT DISTINCT p.* from patient p , analysemedicale a , typeanalysebd t where p.PatientID = a.PatientID and a.TypeanalysebdID = t.TypeanalysebdID and t.CategorieID = "+idCategorie;
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static ResultSet getPatientTypeAnalyse(String idTypeA) {
//         ResultSet res=null;
//         String req="SELECT DISTINCT p.* from patient p , analysemedicale a  where p.PatientID = a.PatientID and a.TypeanalysebdID =  "+idTypeA;
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static ResultSet getPatientIndication(String idIndication) {
//         ResultSet res=null;
//         String req="SELECT DISTINCT p.* from patient p , analysemedicale a  where p.PatientID = a.PatientID and a.Indication = "+idIndication;
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static ResultSet getIdCategorie(String newValue) {
//          ResultSet res=null;
//         String req="SELECT CategorieID from categorie where Nom ='"+newValue+"'";
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static ResultSet getIdTypeAnalyseBD(String newValue) {
//          ResultSet res=null;
//         String req="SELECT TypeanalysebdID from typeanalysebd where Nom ='"+newValue+"'";
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static ResultSet getIdIndication(String newValue) {
//          ResultSet res=null;
//         String req="SELECT IndicationbdID from indicationbd where Nom ='"+newValue+"'";
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//     public static ResultSet getLastAnalyseM() {
//        ResultSet res=null;
//         String req="SELECT MAX(AnalysemedicaleID) from analysemedicale";
//         try {
//             Statement st=myCnx.createStatement();
//             res=st.executeQuery(req);
//         } catch (SQLException e) {
//
//         }
//         return res;
//     }
//
//     public static void AjouterNotificationNouvelle(String string, String idRealisateurChoisi, String string2) {
//         Statement statement;
//         try {
//             statement = myCnx.createStatement();
//             String queryInsert = "INSERT INTO  notifications VALUES ("+string+","+idRealisateurChoisi+",'"+string2+"',0,0,0,0,0,0)";
//             statement.executeUpdate(queryInsert);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
//
//
//
//
//
//    }
