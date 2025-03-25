using System.IO;
using System.Windows;
using Microsoft.Win32;
using System.Diagnostics;



namespace TraveCheckFront
{

    public partial class MainWindow : Window
    {

        string filename;
        string fullFilename;
        int endValue = 0;
        public MainWindow()
        {
            InitializeComponent();

        }

        public void btnFileSelect_Click(object sender, RoutedEventArgs e)
        {
            RunCSVConverter CSVConverter = new RunCSVConverter();
            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.Filter = "Comma Separated Values file | *.csv";
            fileDialog.InitialDirectory = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.UserProfile), "Downloads"); //Automatically will always select the users 'Downloads' folder
            fileDialog.Title = "Please select the CSV file for comparison";

            bool? success = fileDialog.ShowDialog();
            if (success == true)
            {
                filename = fileDialog.SafeFileName; // displays the filename to the user   
                fullFilename = fileDialog.FileName; // collects the full filepath for python to convert it
                tbInfo.Text = filename;
                CSVConverter.CSVConverter(fullFilename, endValue); // passes the file location through to the csv converter
            } else
            {
                //you selected nothing you doofus
            }
        }

    }
}