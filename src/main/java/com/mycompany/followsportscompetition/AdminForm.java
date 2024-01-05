package com.mycompany.followsportscompetition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alper
 */
public class AdminForm extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs, rs1;
    Statement statement;
    ArrayList<String> tableArray = new ArrayList<>();

    public int sportId = -1;
    public int teamSportId = -1;

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

        rbTeams.add(rbBasketball);
        rbTeams.add(rbFootball);
        rbTeams.add(rbVolleyball);

        MatchScoreLoad(tbScores);

    }

    public void MatchLoad(javax.swing.JTable table) {
        ClearTable(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            conn = Db.java_db();
            rs = conn.prepareStatement("SELECT  "
                    + "sm.id, "
                    + "ln.LeagueName,"
                    + " st.SportName, "
                    + "tm1.TeamName AS \"1.Team Name\", "
                    + "tm2.TeamName AS \"2.Team Name\", "
                    + "mt.MatchTime "
                    + "FROM followsports.sportsmatch AS sm "
                    + "JOIN followsports.leaguename AS ln ON sm.LeagueNameId=ln.id "
                    + "JOIN followsports.sport AS st ON sm.SportId=st.id "
                    + "JOIN followsports.team AS tm1 ON  sm.Team1Id=tm1.id "
                    + "JOIN followsports.team AS tm2 ON  sm.Team2Id=tm2.id "
                    + "JOIN followsports.matchtimes AS mt ON sm.MatchTimeId=mt.id;").executeQuery();
            while (rs.next()) {

                String id = String.valueOf(rs.getString("id"));
                String leagueName = String.valueOf(rs.getString("LeagueName"));
                String sportName = String.valueOf(rs.getString("SportName"));
                String team1Name = String.valueOf(rs.getString("1.Team Name"));
                String team2Name = String.valueOf(rs.getString("2.Team Name"));
                String matchTime = String.valueOf(rs.getString("MatchTime"));
                tableArray.add(leagueName + " " + sportName + " " + team1Name + " " + team2Name + " " + matchTime);
                if (!id.equals("") && !leagueName.equals("") && !sportName.equals("") && !team1Name.equals("") && !team2Name.equals("") && !matchTime.equals("")) {
                    Object[] addedTable = {id, leagueName, sportName, team1Name, team2Name, matchTime};
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

    public void ClearTable(javax.swing.JTable table) {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    public void TeamLoad(javax.swing.JComboBox<String> cb) {
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

    public void MatchTimeLoad(javax.swing.JComboBox<String> cb) {
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

    public void MatchScoreLoad(javax.swing.JTable table) {
        ClearTable(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            conn = Db.java_db();
            rs = conn.prepareStatement("SELECT sm.id,st.SportName,ln.LeagueName,tm1.TeamName AS \"1.Team Name\",sm.Team1Score AS \"1.Team Score\",sm.Team2Score AS \"2.Team Score\",\n"
                    + "tm2.TeamName AS \"2.Team Name\",p1.PointScore AS \"1.Team Points\",p2.PointScore AS \"2.Team Points\""
                    + "FROM followsports.sportsmatch AS sm\n"
                    + "JOIN followsports.leaguename AS ln ON sm.LeagueNameId = ln.id "
                    + "JOIN followsports.sport AS st ON sm.SportId = st.id "
                    + "JOIN followsports.team AS tm1 ON sm.Team1Id = tm1.id\n"
                    + "JOIN followsports.team AS tm2 ON sm.Team2Id = tm2.id "
                    + "JOIN followsports.matchtimes AS mt ON sm.MatchTimeId = mt.id "
                    + "LEFT JOIN followsports.points AS p1 ON sm.Team1Id = p1.TeamId "
                    + "LEFT JOIN followsports.points AS p2 ON sm.Team2Id = p2.TeamId "
                    + "WHERE sm.IsActive=1;").executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getString(1));
                String sport = String.valueOf(rs.getString(2));
                String league = String.valueOf(rs.getString(3));
                String team1 = String.valueOf(rs.getString(4));
                String score1 = String.valueOf(rs.getString(5));
                String score2 = String.valueOf(rs.getString(6));
                String team2 = String.valueOf(rs.getString(7));
                String point1 = String.valueOf(rs.getString(8));
                String point2 = String.valueOf(rs.getString(9));

                if (!id.equals("") && !sport.equals("") && !league.equals("") && !team1.equals("") && !score1.equals("") && !score2.equals("") && !team2.equals("") && !point1.equals("") && !point2.equals("")) {
                    Object[] addedTable = {id, sport, league, team1, score1, score2, team2, point1, point2};
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

    public void ClearData() {
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
        rbTeams = new javax.swing.ButtonGroup();
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
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lbMatches = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        rbFootball = new javax.swing.JRadioButton();
        rbBasketball = new javax.swing.JRadioButton();
        rbVolleyball = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbScores = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lblTeam1 = new javax.swing.JLabel();
        lblTeam2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        team1Score = new javax.swing.JSpinner();
        team2Score = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

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
        jLabel4.setText(" Matches");

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

        jButton5.setText("Export");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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
                        .addComponent(fconfirm_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rb_Basketball)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addComponent(rb_Volleyball))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbMatchTime, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbTeam2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbTeam1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbLeague, javax.swing.GroupLayout.Alignment.LEADING, 0, 176, Short.MAX_VALUE)))))
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(fdel_btn)
                        .addGap(137, 137, 137)
                        .addComponent(jButton5)
                        .addGap(289, 289, 289))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
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
                    .addComponent(fdel_btn)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Matches", jPanel3);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jScrollPane4.setViewportView(lbMatches);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ACTİVE MATCHES");

        rbFootball.setText("Football");
        rbFootball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFootballActionPerformed(evt);
            }
        });

        rbBasketball.setText("Basketball");
        rbBasketball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBasketballActionPerformed(evt);
            }
        });

        rbVolleyball.setText("Volleyball");
        rbVolleyball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVolleyballActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Sport:");

        jButton3.setText("Get Matches");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(436, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFootball)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addComponent(rbBasketball)
                                .addGap(52, 52, 52)
                                .addComponent(rbVolleyball))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(270, 270, 270))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFootball)
                    .addComponent(rbBasketball)
                    .addComponent(rbVolleyball)
                    .addComponent(jLabel15))
                .addGap(56, 56, 56)
                .addComponent(jButton3)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Teams", jPanel6);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        tbScores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Sport", "League", "Team 1", "Score 1", "Score 2", "Team 2", "Point 1", "Point 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbScores.setFillsViewportHeight(true);
        tbScores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbScoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbScores);
        if (tbScores.getColumnModel().getColumnCount() > 0) {
            tbScores.getColumnModel().getColumn(0).setMinWidth(60);
            tbScores.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Active Matches and Scores");

        lblTeam1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam1.setText("Team 1");

        lblTeam2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam2.setText("Team 2");

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        team1Score.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        team2Score.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jButton4.setText("Finish the match");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 51, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(70, 70, 70))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(team1Score, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(95, 95, 95))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(team2Score, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(team1Score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lblTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(team2Score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Scores", jPanel1);

        jMenu3.setText("System Out");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        jMenu1.setText("Home Page");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu1);

        jMenu4.setText("Team Operations");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fconfirm_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fconfirm_btnActionPerformed
        /*int leagueId = Integer.parseInt(String.valueOf(cbLeague.getSelectedIndex()));
        int team1Id = Integer.parseInt(String.valueOf(cbTeam1.getSelectedIndex()));
        int team2Id = Integer.parseInt(String.valueOf(cbTeam2.getSelectedIndex()));
        int matchTimeId = Integer.parseInt(String.valueOf(cbMatchTime.getSelectedIndex()));
         */
        int leagueId = cbLeague.getSelectedIndex();
        int team1Id = cbTeam1.getSelectedIndex();
        int team2Id = cbTeam2.getSelectedIndex();
        int matchTimeId = cbMatchTime.getSelectedIndex();
        if (cbLeague.getSelectedIndex() != 0) {
            if (cbTeam1.getSelectedIndex() != 0) {
                if (cbTeam2.getSelectedIndex() != 0) {
                    if (cbMatchTime.getSelectedIndex() != 0) {
                        try {
                            conn = Db.java_db();
                            String sql = "INSERT INTO `followsports`.`sportsmatch` (`LeagueNameId`, `SportId`, `Team1Id`, `Team2Id`, `MatchTimeId`) VALUES ('" + leagueId + "', '" + sportId + "', '" + team1Id + "', '" + team2Id + "', '" + matchTimeId + "');";
                            statement = conn.createStatement();
                            statement.executeUpdate(sql);
                            MatchLoad(tbMatches);
                            ClearData();
                            MatchScoreLoad(tbScores);
                            JOptionPane.showMessageDialog(null, "Match added successfully");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        } finally {
                            try {
                                conn.close();
                            } catch (Exception e) {
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select match time");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose team 2");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please choose team 1");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose league");
        }

    }//GEN-LAST:event_fconfirm_btnActionPerformed

    private void rb_FootballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_FootballActionPerformed
        sportId = 1;
    }//GEN-LAST:event_rb_FootballActionPerformed

    private void rb_BasketballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_BasketballActionPerformed
        sportId = 2;
    }//GEN-LAST:event_rb_BasketballActionPerformed

    private void rb_VolleyballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_VolleyballActionPerformed
        sportId = 3;
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
                String sql = "DELETE FROM `followsports`.`sportsmatch` WHERE (`id` = '" + deletedMatchesId + "');";
                statement = conn.createStatement();
                statement.executeUpdate(sql);
                MatchLoad(tbMatches);
                ClearData();
                JOptionPane.showMessageDialog(null, "Selected match deleted");
            } catch (Exception e) {
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }

        }
    }//GEN-LAST:event_fdel_btnActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        RedirectPageForm frm = new RedirectPageForm();
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu3MouseClicked
    DefaultListModel<String> listModel = new DefaultListModel<>();

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listModel.clear();
        if (teamSportId != -1) {
            try {
                conn = Db.java_db();
                rs = conn.prepareStatement("SELECT tm1.TeamName,sm.Team1Score,sm.Team2Score,tm2.TeamName "
                        + "FROM followsports.sportsmatch sm "
                        + "JOIN followsports.team AS tm1 ON sm.Team1Id=tm1.id "
                        + "JOIN followsports.team AS tm2 ON sm.Team2Id=tm2.id "
                        + "WHERE sm.IsActive=1 and sm.SportId=" + teamSportId + ";").executeQuery();
                while (rs.next()) {
                    String team1 = String.valueOf(rs.getString(1));
                    String team1Score = String.valueOf(rs.getString(2));
                    String team2Score = String.valueOf(rs.getString(3));
                    String team2 = String.valueOf(rs.getString(4));
                    String addedMatch = team1 + " | " + team1Score + " - " + team2Score + " | " + team2;
                    listModel.addElement(addedMatch);
                }
                lbMatches.setModel(listModel);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose sports.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void rbFootballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFootballActionPerformed
        teamSportId = 1;
    }//GEN-LAST:event_rbFootballActionPerformed

    private void rbBasketballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBasketballActionPerformed
        teamSportId = 2;
    }//GEN-LAST:event_rbBasketballActionPerformed

    private void rbVolleyballActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVolleyballActionPerformed
        teamSportId = 3;
    }//GEN-LAST:event_rbVolleyballActionPerformed
    private int previousTeam1Score = -1;
    private int previousTeam2Score = -1;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!selectedId.equals("")) {
            try {
                conn = Db.java_db();
                String sql = "UPDATE `followsports`.`sportsmatch` SET `Team1Score` = ?, `Team2Score` = ? WHERE (`id` = ?);";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, (int) team1Score.getValue());
                preparedStatement.setInt(2, (int) team2Score.getValue());
                preparedStatement.setInt(3, Integer.parseInt(selectedId));

                preparedStatement.executeUpdate();

                MatchScoreLoad(tbScores);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose match");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void updatePoints(int team1Score, int team2Score) {
        try {

            String query = "SELECT Team1Id, Team2Id FROM `followsports`.`sportsmatch` WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(selectedId));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int team1Id = resultSet.getInt("Team1Id");
                int team2Id = resultSet.getInt("Team2Id");

                updatePointsInDatabase(team1Id, team1Score, team2Score);
                updatePointsInDatabase(team2Id, team2Score, team1Score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePointsInDatabase(int teamId, int teamScore, int opponentScore) {
        try {

            int points = 0;
            if (teamScore > opponentScore) {
                points = 3;
            } else if (teamScore == opponentScore) {
                points = 1;
            } else {
                points = 0;
            }

            String getCurrentPointsSQL = "SELECT PointScore FROM points WHERE TeamId = ?";
            PreparedStatement getCurrentPointsStatement = conn.prepareStatement(getCurrentPointsSQL);
            getCurrentPointsStatement.setInt(1, teamId);
            ResultSet currentPointsResultSet = getCurrentPointsStatement.executeQuery();

            int currentPoints = 0;
            if (currentPointsResultSet.next()) {
                currentPoints = currentPointsResultSet.getInt("PointScore");
            }

            int updatedPoints = currentPoints + points;

            String updatePointsSQL = "UPDATE points SET PointScore = ? WHERE TeamId = ?";
            PreparedStatement updatePointsStatement = conn.prepareStatement(updatePointsSQL);
            updatePointsStatement.setInt(1, updatedPoints);
            updatePointsStatement.setInt(2, teamId);
            updatePointsStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    String selectedId = "";
    private void tbScoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbScoresMouseClicked
        int selectedRow = tbScores.getSelectedRow();
        DefaultTableModel dm = (DefaultTableModel) tbScores.getModel();
        if (selectedRow == -1) {
            if (tbScores.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Match table is empty");
            } else {
                JOptionPane.showMessageDialog(null, "Select the match to update");
            }
        } else {
            try {

                conn = Db.java_db();

                selectedId = String.valueOf(dm.getValueAt(selectedRow, 0)).trim();
                lblTeam1.setText(String.valueOf(dm.getValueAt(selectedRow, 3)).trim());
                lblTeam2.setText(String.valueOf(dm.getValueAt(selectedRow, 6)).trim());

                String sql = "SELECT sm.id,st.SportName,ln.LeagueName,tm1.TeamName AS \"1.Team Name\",sm.Team1Score AS \"1.Team Score\",sm.Team2Score AS \"2.Team Score\",tm2.TeamName AS \"2.Team Name\",p1.PointScore AS \"1.Team Points\",p2.PointScore AS \"2.Team Points\" "
                        + "FROM followsports.sportsmatch AS sm\n"
                        + "JOIN followsports.leaguename AS ln ON sm.LeagueNameId = ln.id "
                        + "JOIN followsports.sport AS st ON sm.SportId = st.id "
                        + "JOIN followsports.team AS tm1 ON sm.Team1Id = tm1.id\n"
                        + "JOIN followsports.team AS tm2 ON sm.Team2Id = tm2.id "
                        + "JOIN followsports.matchtimes AS mt ON sm.MatchTimeId = mt.id "
                        + "LEFT JOIN followsports.points AS p1 ON sm.Team1Id = p1.TeamId "
                        + "LEFT JOIN followsports.points AS p2 ON sm.Team2Id = p2.TeamId"
                        + " WHERE sm.IsActive=1 AND sm.id=" + selectedId;
                rs = conn.prepareStatement(sql).executeQuery();

                while (rs.next()) {

                    int score1 = Integer.valueOf(String.valueOf(rs.getString("1.Team Score")));
                    int score2 = Integer.valueOf(String.valueOf(rs.getString("2.Team Score")));
                    team1Score.setValue(score1);
                    team2Score.setValue(score2);
                }

            } catch (Exception e) {
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_tbScoresMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        selectedId = "";
        lblTeam1.setText("Team 1");
        lblTeam2.setText("Team 2");
        team1Score.setValue(0);
        team2Score.setValue(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!selectedId.equals("")) {
            try {
                conn = Db.java_db();

                String matchDetailsQuery = "SELECT Team1Id, Team2Id, Team1Score, Team2Score FROM `followsports`.`sportsmatch` WHERE id = ?";
                PreparedStatement matchDetailsStatement = conn.prepareStatement(matchDetailsQuery);
                matchDetailsStatement.setInt(1, Integer.parseInt(selectedId));
                ResultSet matchDetailsResultSet = matchDetailsStatement.executeQuery();

                if (matchDetailsResultSet.next()) {
                    int team1Id = matchDetailsResultSet.getInt("Team1Id");
                    int team2Id = matchDetailsResultSet.getInt("Team2Id");
                    int team1Score = matchDetailsResultSet.getInt("Team1Score");
                    int team2Score = matchDetailsResultSet.getInt("Team2Score");

                    updatePointsInDatabase(team1Id, team1Score, team2Score);
                    updatePointsInDatabase(team2Id, team2Score, team1Score);

                    String finishMatchSQL = "UPDATE `followsports`.`sportsmatch` SET `IsActive` = '0' WHERE (`id` = ?)";
                    PreparedStatement finishMatchStatement = conn.prepareStatement(finishMatchSQL);
                    finishMatchStatement.setInt(1, Integer.parseInt(selectedId));
                    finishMatchStatement.executeUpdate();

                    MatchScoreLoad(tbScores);
                    JOptionPane.showMessageDialog(null, "Finish the selected match");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please choose match");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        jTabbedPane1.setSelectedIndex(0);

    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        TeamOperationsForm frm = new TeamOperationsForm();
        frm.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Export frm = new Export(tableArray);
        frm.setVisible(true);
        this.hide();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lbMatches;
    private javax.swing.JLabel lblTeam1;
    private javax.swing.JLabel lblTeam2;
    private javax.swing.JRadioButton rbBasketball;
    private javax.swing.JRadioButton rbFootball;
    private javax.swing.ButtonGroup rbSports;
    private javax.swing.ButtonGroup rbTeams;
    private javax.swing.JRadioButton rbVolleyball;
    private javax.swing.JRadioButton rb_Basketball;
    private javax.swing.JRadioButton rb_Football;
    private javax.swing.JRadioButton rb_Volleyball;
    private javax.swing.JTable tbMatches;
    private javax.swing.JTable tbScores;
    private javax.swing.JSpinner team1Score;
    private javax.swing.JSpinner team2Score;
    // End of variables declaration//GEN-END:variables
}
