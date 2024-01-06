package com.mycompany.followsportscompetition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class UserPageForm extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs, rs1;
    Statement statement;
    ArrayList<String> tableArrayq = new ArrayList<>();
    String sportName = "";
    String leagueName = "";
    String teamName = "";

    public UserPageForm() {
        initComponents();

        MatchesLoad(tbMatches, "", "", "");
        SportLoad(cbSportType);
        LeagueLoad(cbLeague);
    }

    public void SportLoad(javax.swing.JComboBox<String> cb) {
        try {
            conn = Db.java_db();
            String sql = "SELECT * FROM followsports.sport;";
            rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                String teamName = String.valueOf(rs.getString("SportName"));
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

    public void LeagueLoad(javax.swing.JComboBox<String> cb) {
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

    public void MatchesLoad(javax.swing.JTable table, String sportName, String leagueName, String nameOfTeam) {
        ClearTable(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            conn = Db.java_db();
            String query = "SELECT\n"
                    + "    sm.id,\n"
                    + "    st.SportName,\n"
                    + "    ln.LeagueName,\n"
                    + "    tm1.TeamName AS \"1.Team Name\",\n"
                    + "    sm.Team1Score AS \"1.Team Score\",\n"
                    + "    sm.Team2Score AS \"2.Team Score\",\n"
                    + "    tm2.TeamName AS \"2.Team Name\",\n"
                    + "    mt.MatchTime AS \"Match Time\",\n"
                    + "    p1.PointScore AS \"1.Team Points\",\n"
                    + "    p2.PointScore AS \"2.Team Points\",\n"
                    + "    CASE\n"
                    + "        WHEN sm.IsActive = 1 THEN 'Active'\n"
                    + "        WHEN sm.IsActive = 0 THEN 'Finished'\n"
                    + "        ELSE 'Unknown'\n"
                    + "    END AS MatchStatus\n"
                    + "FROM\n"
                    + "    followsports.sportsmatch AS sm\n"
                    + "JOIN followsports.leaguename AS ln ON sm.LeagueNameId = ln.id\n"
                    + "JOIN followsports.sport AS st ON sm.SportId = st.id\n"
                    + "JOIN followsports.team AS tm1 ON sm.Team1Id = tm1.id\n"
                    + "JOIN followsports.team AS tm2 ON sm.Team2Id = tm2.id\n"
                    + "JOIN followsports.matchtimes AS mt ON sm.MatchTimeId = mt.id\n"
                    + "LEFT JOIN followsports.points AS p1 ON sm.Team1Id = p1.TeamId\n"
                    + "LEFT JOIN followsports.points AS p2 ON sm.Team2Id = p2.TeamId\n"
                    + "WHERE\n"
                    + "    st.SportName LIKE '" + sportName + "%' AND ln.LeagueName LIKE '" + leagueName + "%' AND (tm1.TeamName LIKE '" + nameOfTeam + "%' OR tm2.TeamName LIKE '" + nameOfTeam + "%');";

            rs = conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getString(1));
                String sport = String.valueOf(rs.getString(2));
                String league = String.valueOf(rs.getString(3));
                String team1 = String.valueOf(rs.getString(4));
                String score1 = String.valueOf(rs.getString(5));
                String score2 = String.valueOf(rs.getString(6));
                String team2 = String.valueOf(rs.getString(7));
                String matchTime = String.valueOf(rs.getString("Match Time"));
                String matchStatus = String.valueOf(rs.getString("MatchStatus"));

                tableArrayq.add(id + " " + sport + " " + league + " " + team1 + " " + score1 + " " + score2 + " " + team2 + " " + matchTime + " " + matchStatus);
                if (!id.equals("") && !sport.equals("") && !league.equals("") && !team1.equals("") && !score1.equals("") && !score2.equals("") && !team2.equals("")) {
                    Object[] addedTable = {id, sport, league, team1, score1, score2, team2, matchTime, matchStatus};
                    model.addRow(addedTable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ClearTable(javax.swing.JTable table) {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTeamName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cbSportType = new javax.swing.JComboBox<>();
        cbLeague = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMatches = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbSportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbSportType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSportTypeMouseClicked(evt);
            }
        });

        cbLeague.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbLeague.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbLeagueMouseClicked(evt);
            }
        });

        tbMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Sport", "League", "Team 1", "Score 1", "Score 2", "Team 2", "MatchTime", "IsActive"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbMatches);

        jButton2.setText("List All Matches");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Export");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbSportType, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbLeague, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTeamName)
                    .addComponent(cbSportType)
                    .addComponent(cbLeague)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jMenu1.setText("System Out");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Matches");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Team Standings");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        RedirectPageForm frm = new RedirectPageForm();
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        teamName = txtTeamName.getText().toString();
        leagueName = cbLeague.getSelectedItem().toString();
        sportName = cbSportType.getSelectedItem().toString();
        MatchesLoad(tbMatches, sportName, leagueName, teamName);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbLeagueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbLeagueMouseClicked

    }//GEN-LAST:event_cbLeagueMouseClicked

    private void cbSportTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSportTypeMouseClicked


    }//GEN-LAST:event_cbSportTypeMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        MatchesLoad(tbMatches, "", "", "");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        UserPageForm frm = new UserPageForm();
        frm.show();
        this.hide();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        TeamStandingsForm frm = new TeamStandingsForm();
        frm.show();
        this.hide();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Export frm = new Export(tableArrayq);
        frm.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(UserPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserPageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserPageForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbLeague;
    private javax.swing.JComboBox<String> cbSportType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbMatches;
    private javax.swing.JTextField txtTeamName;
    // End of variables declaration//GEN-END:variables
}
