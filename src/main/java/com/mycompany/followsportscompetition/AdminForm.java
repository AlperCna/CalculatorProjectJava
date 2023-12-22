/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.followsportscompetition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Database.Db;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huseyinkaradana
 */
public class AdminForm extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs, rs1;
    Statement statement;
    
    public int sportId=-1;
    
    public AdminForm() {
        initComponents();
        rbSports.add(rb_Football);
        rbSports.add(rb_Basketball);
        rbSports.add(rb_Volleyball);
        TeamLoad(cbTeam1);
        TeamLoad(cbTeam2);
        MatchTimeLoad(cbMatchTime);
        LeagueLoad(cbLeague);
        MatchLoad(tbMatches);
    }
    
    public void MatchLoad(javax.swing.JTable table){
        ClearTable(table);
         DefaultTableModel model = (DefaultTableModel) tbMatches.getModel();
        try {
            conn = Db.java_db();
            rs = conn.prepareStatement("SELECT  sm.id, ln.LeagueName, st.SportName, tm1.TeamName AS \"1.Team Name\", tm2.TeamName AS \"2.Team Name\", mt.MatchTime FROM followsports.sportsmatch AS sm JOIN followsports.leaguename AS ln ON sm.LeagueNameId=ln.id JOIN followsports.sport AS st ON sm.SportId=st.id JOIN followsports.team AS tm1 ON  sm.Team1Id=tm1.id JOIN followsports.team AS tm2 ON  sm.Team2Id=tm2.id JOIN followsports.matchtimes AS mt ON sm.MatchTimeId=mt.id;").executeQuery();
            while (rs.next()) { 
                String id = String.valueOf(rs.getString("id"));
                String leagueName = String.valueOf(rs.getString("LeagueName"));
                String sportName = String.valueOf(rs.getString("SportName"));
                String team1Name = String.valueOf(rs.getString("1.Team Name"));
                String team2Name = String.valueOf(rs.getString("2.Team Name"));
                String matchTime = String.valueOf(rs.getString("MatchTime"));
                
                if(!id.equals("")&&!leagueName.equals("")&&!sportName.equals("")&&!team1Name.equals("")&&!team2Name.equals("")&&!matchTime.equals("")){
                    Object[] addedTable = {id,leagueName,sportName,team1Name,team2Name,matchTime};
                    model.addRow(addedTable);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void ClearTable(javax.swing.JTable table){
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }
    public void TeamLoad(javax.swing.JComboBox<String> cb){
         try {
            conn = Db.java_db();
            String sql = "SELECT * FROM followsports.team";
            rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                String teamName = String.valueOf(rs.getString("TeamName"));
                cb.addItem(teamName);
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
    public void LeagueLoad(javax.swing.JComboBox<String> cb){
        try {
            conn = Db.java_db();
            String sql = "SELECT * FROM followsports.leaguename;";
            rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                String teamName = String.valueOf(rs.getString("LeagueName"));
                cb.addItem(teamName);
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
    public void MatchTimeLoad(javax.swing.JComboBox<String> cb){
         try {
            conn = Db.java_db();
            String sql = "SELECT * FROM followsports.matchtimes";
            rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                String teamName = String.valueOf(rs.getString("MatchTime"));
                cb.addItem(teamName);
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
    public void ClearData(){
        cbLeague.setSelectedIndex(0);
        cbMatchTime.setSelectedIndex(0);
        cbTeam1.setSelectedIndex(0);
        cbTeam2.setSelectedIndex(0);
        rbSports.clearSelection();
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbSports = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fdel_btn = new javax.swing.JButton();
        cbTeam1 = new javax.swing.JComboBox<>();
        cbTeam2 = new javax.swing.JComboBox<>();
        cbMatchTime = new javax.swing.JComboBox<>();
        fconfirm_btn = new javax.swing.JButton();
        rb_Football = new javax.swing.JRadioButton();
        rb_Basketball = new javax.swing.JRadioButton();
        rb_Volleyball = new javax.swing.JRadioButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMatches = new javax.swing.JTable();
        cbLeague = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        teams_list = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        t_fteams = new javax.swing.JRadioButton();
        t_bteams = new javax.swing.JRadioButton();
        t_vteams = new javax.swing.JRadioButton();
        radioPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        scores_tbl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("First Team");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Second Team");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Match Time");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Active Matches");

        fdel_btn.setText("Delete");
        fdel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdel_btnActionPerformed(evt);
            }
        });

        cbTeam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please choose team" }));

        cbTeam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please choose team" }));

        cbMatchTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Time" }));

        fconfirm_btn.setText("Confirm");
        fconfirm_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fconfirm_btnActionPerformed(evt);
            }
        });

        rb_Football.setText("Football");
        rb_Football.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_FootballActionPerformed(evt);
            }
        });

        rb_Basketball.setText("Basketball");
        rb_Basketball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_BasketballActionPerformed(evt);
            }
        });

        rb_Volleyball.setText("Volleyball");
        rb_Volleyball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_VolleyballActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Add to");

        tbMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "League Name", "Sport Name", "1. Team Name", "2. Team Name", "Match Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbMatches);

        cbLeague.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please choose league" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("League");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(rb_Football)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fconfirm_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_Basketball))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(rb_Volleyball)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbLeague, 0, 150, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbMatchTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cbTeam2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(jLabel2))
                                .addComponent(cbTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fdel_btn)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLeague, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMatchTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_Football)
                            .addComponent(rb_Basketball)
                            .addComponent(rb_Volleyball))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fconfirm_btn)
                    .addComponent(fdel_btn))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Matches", jPanel3);

        jScrollPane4.setViewportView(teams_list);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ACTİVE TEAMS");

        t_fteams.setText("Football");

        t_bteams.setText("Basketball");

        t_vteams.setText("Volleyball");

        javax.swing.GroupLayout radioPanelLayout = new javax.swing.GroupLayout(radioPanel);
        radioPanel.setLayout(radioPanelLayout);
        radioPanelLayout.setHorizontalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        radioPanelLayout.setVerticalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(313, 313, 313))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(t_fteams)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t_bteams)
                                .addGap(52, 52, 52)
                                .addComponent(t_vteams))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(radioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(270, 270, 270))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_fteams)
                    .addComponent(t_bteams)
                    .addComponent(t_vteams))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Teams", jPanel6);

        scores_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scores_tbl.setFillsViewportHeight(true);
        jScrollPane3.setViewportView(scores_tbl);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Active Matches and Scores");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Home Team Score Update");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Away Team Score Update");

        jButton1.setText("Update");

        jButton2.setText("Update");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 15, 1));

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("Scores", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fconfirm_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fconfirm_btnActionPerformed
        int leagueId=Integer.parseInt(String.valueOf(cbLeague.getSelectedIndex()));
        int team1Id=Integer.parseInt(String.valueOf(cbTeam1.getSelectedIndex()));
        int team2Id=Integer.parseInt(String.valueOf(cbTeam2.getSelectedIndex()));
        int matchTimeId=Integer.parseInt(String.valueOf(cbMatchTime.getSelectedIndex()));
        if(cbLeague.getSelectedIndex()!=0){
            if(cbTeam1.getSelectedIndex()!=0){
                if(cbTeam2.getSelectedIndex()!=0){
                    if(cbMatchTime.getSelectedIndex()!=0){
                        try{
                            conn = Db.java_db();
                            String sql = "INSERT INTO `followsports`.`sportsmatch` (`LeagueNameId`, `SportId`, `Team1Id`, `Team2Id`, `MatchTimeId`) VALUES ('"+leagueId+"', '"+sportId+"', '"+team1Id+"', '"+team2Id+"', '"+matchTimeId+"');";
                            statement = conn.createStatement();
                            statement.executeUpdate(sql);
                            MatchLoad(tbMatches);
                            ClearData();
                            JOptionPane.showMessageDialog(null, "Match added successfully");  
                        }catch(Exception e){
                           JOptionPane.showMessageDialog(null, e);  
                        }finally{
                            try {
                                conn.close();
                            } catch (Exception e) {
                            }
                        }
                    }else{
                       JOptionPane.showMessageDialog(null, "Please select match time");      
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please choose team 2");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please choose team 1");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Please choose league"); 
        }
       
    }//GEN-LAST:event_fconfirm_btnActionPerformed

    private void rb_FootballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_FootballActionPerformed
        sportId=1;
    }//GEN-LAST:event_rb_FootballActionPerformed

    private void rb_BasketballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_BasketballActionPerformed
       sportId=2;    
    }//GEN-LAST:event_rb_BasketballActionPerformed

    private void rb_VolleyballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_VolleyballActionPerformed
        sportId=3;     
    }//GEN-LAST:event_rb_VolleyballActionPerformed

    private void fdel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdel_btnActionPerformed
       int selectedMatch = tbMatches.getSelectedRow();
        DefaultTableModel dm = (DefaultTableModel) tbMatches.getModel();
        if (selectedMatch == -1) {
            if (tbMatches.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Matches table is empty");
            } else {
                JOptionPane.showMessageDialog(null, "Select the match to delete");
            }
        } else {
            String deletedMatchesId = String.valueOf(dm.getValueAt(selectedMatch, 0)).trim();
            
            System.out.println(deletedMatchesId);
            
            try {
                conn = Db.java_db();
                String sql = "DELETE FROM `followsports`.`sportsmatch` WHERE (`id` = '"+deletedMatchesId+"');";
                statement=conn.createStatement();
                statement.executeUpdate(sql);
                MatchLoad(tbMatches);
                ClearData();
                JOptionPane.showMessageDialog(null,"Selected match deleted");
            } catch (Exception e) {
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
            
            
        }
    }//GEN-LAST:event_fdel_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLeague;
    private javax.swing.JComboBox<String> cbMatchTime;
    private javax.swing.JComboBox<String> cbTeam1;
    private javax.swing.JComboBox<String> cbTeam2;
    private javax.swing.JButton fconfirm_btn;
    private javax.swing.JButton fdel_btn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel radioPanel;
    private javax.swing.ButtonGroup rbSports;
    private javax.swing.JRadioButton rb_Basketball;
    private javax.swing.JRadioButton rb_Football;
    private javax.swing.JRadioButton rb_Volleyball;
    private javax.swing.JTable scores_tbl;
    private javax.swing.JRadioButton t_bteams;
    private javax.swing.JRadioButton t_fteams;
    private javax.swing.JRadioButton t_vteams;
    private javax.swing.JTable tbMatches;
    private javax.swing.JList<String> teams_list;
    // End of variables declaration//GEN-END:variables
}
