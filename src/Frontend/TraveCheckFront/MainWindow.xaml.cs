using System.IO;
using System.Windows;
using Microsoft.Win32;
using Python.Runtime;



namespace TraveCheckFront
{
   
    public partial class MainWindow : Window
    {
        
        string filename;
        string fullFilename;
        public MainWindow()
        {
            InitializeComponent();
        }

        public void btnFileSelect_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.Filter = "Comma Separated Values file | *.csv";
            fileDialog.InitialDirectory = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.UserProfile), "Downloads"); //Automatically will always select the users 'Downloads' folder
            fileDialog.Title = "Please select the CSV file for comparison";

            bool? success = fileDialog.ShowDialog();
            if (success == true)
            {
                filename = fileDialog.SafeFileName;
                fullFilename = fileDialog.FileName;
                tbInfo.Text = filename;
                RunCSVConverter(fullFilename);
                Console.ReadLine();
                
            } else
            {
                //you selected nothing you doofus
            }
        }

        static void RunCSVConverter(string fullFilename) {
            Runtime.PythonDLL = "C:\\Users\\Stijn\\AppData\\Local\\Programs\\Python\\Python312\\python312.dll";
            PythonEngine.Initialize();
            using (Py.GIL())
            {
                var PythonScript = Py.Import("csvreader");
                var message = new PyString($"{fullFilename}");
                var meowsies = PythonScript.InvokeMethod("csvreaderxmlwriter", new PyObject[] {message});
            }

            
        }

    }
}