import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class tableModel extends AbstractTableModel {
	private Vector content = null;
	private String[] title_name = { "�ļ���", "�ļ�·��", "�ļ�����", "�ļ���С/KB", "�޸�����" };

	public tableModel() {
		content = new Vector();
	}

	public void addRow(myFile myFile) {
		Vector v = new Vector();
		DecimalFormat format = new DecimalFormat("#0.00");  //���ڸ�ʽ������,��ʽΪ#0.00
		v.add(0, myFile.getFileName());  //�ļ���
		v.add(1, myFile.getFilePath());  //�ļ�·��
		if (myFile.getMyFile().isFile()) {
			v.add(2, "�ļ�");
			v.add(3, format.format(myFile.getSpace())); //�ļ���С
		} else {
			v.add(2, "�ļ���");
			v.add(3, "-");
		}
		long time = myFile.getMyFile().lastModified();  //�ļ������һ�α༭ʱ��
		String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
		v.add(4, ctime);
		content.add(v);
	}

	public void removeRows(int row, int count) {
		for (int i = 0; i < count; i++) {
			if (content.size() > row) {
				content.remove(row);
			}
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int colIndex) {
		((Vector) content.get(rowIndex)).remove(colIndex);
		((Vector) content.get(rowIndex)).add(colIndex, value);
		this.fireTableCellUpdated(rowIndex, colIndex);
	}

	public String getColumnName(int col) {
		return title_name[col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return content.size();
	}

	@Override
	public int getColumnCount() {
		return title_name.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector) content.get(rowIndex)).get(columnIndex);
	}
}