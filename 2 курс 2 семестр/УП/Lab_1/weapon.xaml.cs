using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Lab_1.lab_1DataSetTableAdapters;

namespace Lab_1
{
    /// <summary>
    /// Логика взаимодействия для weapon.xaml
    /// </summary>
    public partial class weapon : Window
    {
        public weapon()
        {
            InitializeComponent();
            weaponTableAdapter weaponTable = new weaponTableAdapter();
            datagrid.ItemsSource = weaponTable.GetData();
            combobox.ItemsSource = weaponTable.GetData();
            combobox.DisplayMemberPath = "name";
        }
        private void combobox_SelectionChanged_1(object sender, SelectionChangedEventArgs e)
        {
            object cell = (combobox.SelectedItem as DataRowView).Row[3];
            MessageBox.Show(cell.ToString());
        }
    }
}
