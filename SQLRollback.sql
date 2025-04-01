CREATE DEFINER=`InfraGoAdmin`@`%` PROCEDURE `selectForEquation`(IN p_Personeelsnummer INT)
BEGIN
select 
we.Personeelsnummer,
we.Personeelsnaam,
we.EmailAdres, 
we.Reden_voor_reis as 'Reden voor reis',
we.Methode_van_vervoer as 'Methode van vervoer',
bed.bedrijfsnaam,
bed.afdeling,
oor.VliegveldID,
oor.Land_van_oorsprong as 'Land van oorsprong',
oor.Vertrekdatum,
aan.VliegveldID,
aan.Land_van_aankomst as 'Land van aankomst',
aan.aankomstdatum


from werknemer we
join bedrijven bed on we.EmailAdres = bed.EmailAdres
join aankomstland aan on we.Personeelsnummer = aan.Personeelsnummer
join oorsprongsland oor on we.Personeelsnummer = oor.Personeelsnummer

where we.Personeelsnummer = p_Personeelsnummer
;
END
