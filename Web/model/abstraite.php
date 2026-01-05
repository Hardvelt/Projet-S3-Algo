<?php
trait Connection {
    protected $connection;

    // Méthode d'initialisation à appeler depuis la classe
    public function initConnection($connection) {
        $this->connection = $connection;
    }
}