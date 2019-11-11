package com.ast.maven_sample.Controller;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
public class sample {
	
	public static void main(String[] args) {

        File root = new File(System.getProperty("user.home"));
        FileTreeModel model = new FileTreeModel(root);
        JTree tree = new JTree(model);
        JTree[] tr = new JTree[] { tree };
        JComboBox combo = new JComboBox(tr);
        TreeComboRenderer renderer = new TreeComboRenderer();
        renderer.setPreferredSize(new Dimension(400, 330));
        combo.setRenderer(renderer);
        combo.setMaximumRowCount(3);
        JFrame frame = new JFrame("FileTree");
        frame.getContentPane().add(combo, "North");
        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
}

/**
* The methods in this class allow the JTree component to traverse the file
* system tree, and display the files and directories.
*/
class FileTreeModel implements TreeModel {
   // We specify the root directory when we create the model.
   protected File root;

   public FileTreeModel(File root) {
        this.root = root;
   }

   // The model knows how to return the root object of the tree
   public Object getRoot() {
        return root;
   }

   // Tell JTree whether an object in the tree is a leaf or not
   public boolean isLeaf(Object node) {
        return ((File) node).isFile();
   }

   // Tell JTree how many children a node has
   public int getChildCount(Object parent) {
        String[] children = ((File) parent).list();
        if (children == null)
             return 0;
        return children.length;
   }

   // Fetch any numbered child of a node for the JTree.
   // Our model returns File objects for all nodes in the tree. The
   // JTree displays these by calling the File.toString() method.
   public Object getChild(Object parent, int index) {
        String[] children = ((File) parent).list();
        if ((children == null) || (index >= children.length))
             return null;
        return new File((File) parent, children[index]);
   }

   // Figure out a child's position in its parent node.
   public int getIndexOfChild(Object parent, Object child) {
        String[] children = ((File) parent).list();
        if (children == null)
             return -1;
        String childname = ((File) child).getName();
        for (int i = 0; i < children.length; i++) {
             if (childname.equals(children))

                  return i;

        }

        return -1;

   }



   // This method is only invoked by the JTree for editable trees.

   // This TreeModel does not allow editing, so we do not implement

   // this method. The JTree editable property is false by default.

   public void valueForPathChanged(TreePath path, Object newvalue) {

   }



   // Since this is not an editable tree model, we never fire any events,

   // so we don't actually have to keep track of interested listeners.

   public void addTreeModelListener(TreeModelListener l) {

   }



   public void removeTreeModelListener(TreeModelListener l) {

   }

}



class TreeComboRenderer extends JTree implements ListCellRenderer {

   /**

   * 

   */

   private static final long serialVersionUID = 2504487055200674699L;

   JCheckBox checkBox;

   JScrollPane scrollpane;

   JPanel panel;



   public TreeComboRenderer() {

        File root = new File(System.getProperty("user.home"));

        FileTreeModel model = new FileTreeModel(root);

        this.setModel(model);

        scrollpane = new JScrollPane(this);

   }



   public Component getListCellRendererComponent(JList list, Object value,

             int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {

             setBackground(list.getSelectionBackground());

             setForeground(list.getSelectionForeground());

        } else {

             setBackground(list.getBackground());

             setForeground(list.getForeground());

        }

        return scrollpane;

   }

}

