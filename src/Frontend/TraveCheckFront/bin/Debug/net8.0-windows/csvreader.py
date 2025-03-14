from pathlib import Path
import os
import pandas as pd


def csvreaderxmlwriter(filename):
    file_path = Path(os.getcwd()).parents[3] / "TraveCheckFront" / "XmlFolder" / "XmlUnformedData.xml"  
    df = pd.read_csv(filename)
    with open(file_path, "w") as file:
        file.write(df.to_xml(index = False, root_name='Werknemers', row_name='Werknemer'))
        file.close
  



