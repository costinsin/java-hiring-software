import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends Page {
    private JPanel adminPanel;
    private JButton logOutButton;
    private JList<User> userList;
    private JTree companyTree;
    private JTextArea infoArea;

    public AdminPage() {
        Application instance = Application.getInstance();

        DefaultListModel<User> userModel = new DefaultListModel<>();
        for (User user : instance.users) {
            userModel.addElement(user);
        }
        userList.setModel(userModel);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Application");
        for (Company company : instance.companies) {
            DefaultMutableTreeNode companyNode = new DefaultMutableTreeNode(company);
            companyNode.add(new DefaultMutableTreeNode(company.manager));

            for (Department department : company.departments) {
                DefaultMutableTreeNode departmentNode = new DefaultMutableTreeNode(department);
                DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("Employees");
                DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode("Jobs");

                for (Employee employee : department.employees) {
                    employeeNode.add(new DefaultMutableTreeNode(employee));
                }

                for (Job job : department.jobs) {
                    jobNode.add(new DefaultMutableTreeNode(job));
                }

                if (department.employees.size() != 0)
                    departmentNode.add(employeeNode);
                if (department.jobs.size() != 0)
                    departmentNode.add(jobNode);
                companyNode.add(departmentNode);
            }

            rootNode.add(companyNode);
        }
        TreeModel companyModel = new DefaultTreeModel(rootNode);
        companyTree.setModel(companyModel);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePage(new LoginPage().getPanel());
            }
        });
        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                User selected = userList.getSelectedValue();
                infoArea.setText(selected.resume.toString());
            }
        });
        companyTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) companyTree.getLastSelectedPathComponent();
                Object nodeContent = selectedNode.getUserObject();
                if (nodeContent.getClass().getName().equalsIgnoreCase("Employee") ||
                        nodeContent.getClass().getName().equalsIgnoreCase("Recruiter") ||
                        nodeContent.getClass().getName().equalsIgnoreCase("Manager"))
                    infoArea.setText(((Consumer) nodeContent).resume.toString());
                else if (Department.class.isAssignableFrom(nodeContent.getClass())) {
                    infoArea.setText("Total Salary Budget: " + (Double) ((Department) nodeContent).getTotalSalaryBudget());
                } else if (nodeContent.getClass().getName().equalsIgnoreCase("Job")) {
                    Job job = (Job) nodeContent;
                    infoArea.setText(job.jobName + " at " + job.companyName + " with " + job.noPositions + " open positions.");
                }
            }
        });
    }

    @Override
    JPanel getPanel() {
        return adminPanel;
    }
}
