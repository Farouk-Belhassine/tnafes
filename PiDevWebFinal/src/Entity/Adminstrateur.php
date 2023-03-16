<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Adminstrateur
 *
 * @ORM\Table(name="adminstrateur")
 * @ORM\Entity
 */
class Adminstrateur
{
    /**
     * @var \Utilisateur
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_admin", referencedColumnName="id")
     * })
     */
    private $idAdmin;

    public function getIdAdmin(){return $this->idAdmin;}

    public function setIdAdmin(?Utilisateur $idAdmin){$this->idAdmin = $idAdmin;}


}
