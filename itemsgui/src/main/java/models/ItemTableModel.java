package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Item;

public class ItemTableModel extends AbstractTableModel {
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_PRICE = 2;
    private static final int COLUMN_QUANTITY = 3;

    private String[] columnNames = { "ID", "Name", "Price", "Quantity" };
    private boolean[] canEdit = new boolean[] {
            false, true, true, true
    };
    private ArrayList<Item> items;
    private ArrayList<Item> itemClone;

    public ItemTableModel(List<Item> items) {
        this.items = new ArrayList<Item>(items);
        this.itemClone = new ArrayList<Item>(items);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (items.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = items.get(rowIndex);
        Object returnValue = null;

        switch (columnIndex) {
            case COLUMN_ID:
                returnValue = item.getId();
                break;
            case COLUMN_NAME:
                returnValue = item.getName();
                break;
            case COLUMN_PRICE:
                returnValue = item.getPrice();
                break;
            case COLUMN_QUANTITY:
                returnValue = item.getQuantity();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Item item = items.get(rowIndex);

        switch (columnIndex) {
            case COLUMN_NAME:
                item.setName((String) value);
                break;
            case COLUMN_PRICE:
                item.setPrice((float) value);
                break;
            case COLUMN_QUANTITY:
                item.setQuantity((int) value);
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getRowItem(int row) {
        return items.get(row);
    }

    public void search(String searchTerm) {
        if (searchTerm.equals("")) {
            items = itemClone;
        }

        items = new ArrayList<Item>(itemClone.stream().filter(item -> item.getName().contains(searchTerm)).toList());
        fireTableDataChanged();
    }
}
