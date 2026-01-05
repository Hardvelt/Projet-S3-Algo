<?php
trait getSet
{
    // Getter magique
    public function __get($name) {
        if (property_exists($this, $name)) {
            return $this->$name;
        }

        trigger_error("Propriété '$name' inexistante dans " . static::class, E_USER_NOTICE);
        return null;
    }

    // Setter magique
    public function __set($name, $value) {
        if (property_exists($this, $name)) {
            $this->$name = $value;
            return;
        }

        trigger_error("Propriété '$name' inexistante dans " . static::class, E_USER_NOTICE);
    }
}
?>