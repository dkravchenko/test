package net.sam_solutions.courses.dkrauchanka.panels;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.markup.html.panel.Panel;

public class TablePanel<T> extends Panel{
    private AjaxFallbackDefaultDataTable<T> table;
        
    public TablePanel(String name, AjaxFallbackDefaultDataTable<T> table){
        super(name);
        this.table = table;
        this.add(table);
    }
        
    public void refreshTable(AjaxFallbackDefaultDataTable<T> table){
        this.remove(this.table);
        this.table = table;
        this.add(table);
    }
}