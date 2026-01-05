<?php
trait sqlQuerries
{
    // Récupérer toutes les propriétés, même private/protected
    public function attributs(): array {
        $reflection = new ReflectionClass($this);
        $attributs = $reflection->getProperties();
        $resultat = [];
        foreach ($attributs as $attribut) {
            $attribut->setAccessible(true);
            $resultat[$attribut->getName()] = $attribut->getValue($this);
        }
        return $resultat;
    }

    // Lister tous les voitures
    public function getAll()
    {
        $attributs = $this->attributs();

        // On retire la connexion si elle existe
        unset($attributs['connection']);

        // Générer la liste des colonnes à mettre à jour
        $table = get_class($this);

        $query = "SELECT * FROM  $table";
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getOne(string $idColonne, $idValue)
    {
        $attributs = $this->attributs();

        // On retire la connexion si elle existe
        unset($attributs['connection']);

        // Générer la liste des colonnes à mettre à jour
        $table = get_class($this);

        $query = "SELECT * FROM $table WHERE $idColonne = ?";
        $stmt = $this->conn->prepare($query);
        $stmt->execute([$idValue]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function create(): bool
    {
        $attributs = $this->attributs();

        // On retire la connexion si elle existe
        unset($attributs['connection']);
        unset($attributs['connection']);
        // Générer la liste des colonnes à mettre à jour
        $table = get_class($this);
        $colonnes = array_keys($attribut);
        $stringColonnes = implode(', ', $colonnes);
        $valParts = array_map(fn($col) => ":$col", $colonnes);
        $valString = implode(', ', $setParts);

        try {
        $query = "INSERT INTO $table ($stringColonnes) VALUES ($valString)";
        $stmt = $this->conn->prepare($query);
        $success = $stmt->execute($attributs);
        return $success;
        // si id en auto incrémente return $this->conn->lastInsertId();
     } catch(PDOException $e) {
        print "Error!: " . $e->getMessage() . "</br>";
        return false;
    }

    }

    public function updateTable(string $idColonne, $idValue) {
        $attributs = $this->attributs();

        // On retire la connexion si elle existe
        unset($attributs['connection']);

        // Générer la liste des colonnes à mettre à jour
        $table = get_class($this);
        $colonnes = array_keys($attributs);
        $setParts = array_map(fn($col) => "$col = :$col", $colonnes);
        $setString = implode(', ', $setParts);

        $query = "UPDATE $table SET $setString WHERE $idColonne = :idValue";
        $stmt = $this->connection->prepare($query);

        // Préparer les valeurs pour PDO
        $values = $attributs;
        $values['idValue'] = $idValue;

        return $stmt->execute($values);
    }

    public function delete($string $idColonne, $idValue): bool
    {
        $attributs = $this->attributs();

        // On retire la connexion si elle existe
        unset($attributs['connection']);

        // Générer la liste des colonnes à mettre à jour
        $table = get_class($this);

        try {
        $query = "DELETE FROM $table WHERE $idColonne = ?";
        $stmt = $this->conn->prepare($query);
        $sucess=$stmt->execute([$idValue]);
        //retourne toujours true même si la voture n'existe pas, 
        $sucess= $stmt->rowCount();
        //retourne le nombre de lignes modifiées 
        return $sucess;

        } catch(PDOException $e) {
            print "Error!: " . $e->getMessage() . "</br>";
            return false;
        }
    }
    
}
?>