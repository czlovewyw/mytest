/**
 * 
 */
package org.cz.inspector.gui;


import org.cz.inspector.gui.nodeviewer.ZooInspectorNodeViewer;

import java.util.List;

/**
 * @author CGSmithe
 * 
 */
public interface NodeViewersChangeListener
{
	/**
	 * @param newViewers
	 */
	public void nodeViewersChanged(List<ZooInspectorNodeViewer> newViewers);
}
