from pathlib import Path
import os
import pandas as pd


def csvreaderxmlwriter(filename):
    file_path = Path(os.getcwd()).parents[3] / "TraveCheckFront" / "XmlFolder" / "XmlUnformedData.xml"  # selects the travecheckfront folder 
    df = pd.read_csv(filename)
    with open(file_path, "w") as file: # writes/creates xmlunformeddata for reading by Java program
        file.write(df.to_xml(index = False, root_name='Werknemers', row_name='Werknemer'))
        file.close
    return 1
  



