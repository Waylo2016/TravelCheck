import pandas as pd

df = pd.read_csv('Project Stijn - Blad1.csv')

f = open("XmlUnformedData.xml", "w")
f.write(df.to_xml(index = False, root_name='Werknemers', row_name='Werknemer'))
f.close