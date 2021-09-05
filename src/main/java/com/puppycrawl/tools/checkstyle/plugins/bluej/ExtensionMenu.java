////////////////////////////////////////////////////////////////////////////////
// BlueJ Checkstyle extension:
//    Checks Java source code for adherence to a set of rules.
// Copyright (C) 2003-2004  Rick Giles
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package com.puppycrawl.tools.checkstyle.plugins.bluej;

import bluej.extensions2.BClass;
import bluej.extensions2.BObject;
import bluej.extensions2.BPackage;
import bluej.extensions2.MenuGenerator;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.StageStyle;

/**
 * Manages the Checkstyle extension menu item
 * @author Rick Giles
 * @version $Id: ExtensionMenu.java,v 1.5 2007/08/19 03:13:53 stedwar2 Exp $
 */
public class ExtensionMenu extends MenuGenerator
{
//    /**
//     * @see bluej.extensions.MenuGenerator#getToolsMenuItem(bluej.extensions.BPackage)
//     */
//    public JMenuItem getToolsMenuItem(BPackage aPackage) {
//        final JMenuItem item = new JMenuItem("Checkstyle");
//        item.addActionListener(new MenuAction());
//        return item;
//    }
//
//    /**
//     * @see bluej.extensions.MenuGenerator#getMenuItem()
//     * @deprecated
//     * Deprecated as of BlueJ 1.3.5.
//     * Added for compatibility with BlueJ 1.3.0.
//     */
//    public JMenuItem getMenuItem() {
//        final JMenuItem item = new JMenuItem("Checkstyle");
//        item.addActionListener(new MenuAction());
//        return item;
//    }
//
//    /**
//     * Action listener for the Checkstyle menu item.
//     * Audits files of the current package.
//     * @author Rick Giles
//     * @version 13-May-2003
//     */
//    class MenuAction implements ActionListener
//    {
//        /**
//         * Audits the open projects and shows the results.
//         * @see java.awt.event.ActionListener
//         */
//        public void actionPerformed(ActionEvent aEvent)
//        {
//            CheckstyleExtension.getInstance().showAuditFrame();
//        }
//    }

    private BPackage curPackage;
    private BClass curClass;
    private BObject curObject;
    private EventHandler menAction = menuAction();

    public MenuItem getToolsMenuItem(BPackage aPackage) {
        MenuItem mi = new MenuItem();
        mi.setText("Checkstyle");
        mi.setId("Tools menu:");
        mi.setOnAction(menAction);
        return mi;
    }

    // TODO: change below
    // These methods will be called when
    // each of the different menus are about to be invoked.
    public void notifyPostToolsMenu(BPackage bp, MenuItem mi) {
        System.out.println("Post on Tools menu");
        curPackage = bp;
        curClass = null;
        curObject = null;
    }

    public void notifyPostClassMenu(BClass bc, MenuItem mi) {
        System.out.println("Post on Class menu");
        curPackage = null;
        curClass = bc;
        curObject = null;
    }

    public void notifyPostObjectMenu(BObject bo, MenuItem mi) {
        System.out.println("Post on Object menu");
        curPackage = null;
        curClass = null;
        curObject = bo;
    }

    public void notifyPostPackageMenu(BPackage bp, MenuItem menuItem) {
        System.out.println("Post on Package menu");
        curPackage = bp;
        curClass = null;
        curObject = null;
    }

    // A utility method which pops up a dialog detailing the objects
    public EventHandler menuAction() {
        return actionEvent ->
        {
            try {
                if (curObject != null)
                    curClass = curObject.getBClass();
                if (curClass != null)
                    curPackage = curClass.getPackage();

                String msg = ((MenuItem) actionEvent.getSource()).getId();
                if (curPackage != null)
                    msg += "\nCurrent Package = " + curPackage;
                if (curClass != null)
                    msg += "\nCurrent Class = " + curClass;
                if (curObject != null)
                    msg += "\nCurrent Object = " + curObject;

                Alert dlg = new Alert(AlertType.NONE, msg, ButtonType.OK);
                dlg.initStyle(StageStyle.UTILITY);
                dlg.showAndWait();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        };
    }

}
