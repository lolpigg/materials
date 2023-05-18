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
    /// Логика взаимодействия для potion.xaml
    /// </summary>
    public partial class potion : Window
    {
        public potion()
        {
            InitializeComponent();
            potionTableAdapter potionTable = new potionTableAdapter();
            datagrid.ItemsSource = potionTable.GetData();
            combobox.ItemsSource = potionTable.GetData();
            combobox.DisplayMemberPath = "potion_type";
        }

        private void combobox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            object cell = (combobox.SelectedItem as DataRowView).Row[1];
            MessageBox.Show(cell.ToString());
        }
    }
}
