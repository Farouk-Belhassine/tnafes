<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210426123747 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE abonnement DROP FOREIGN KEY fk_idclientab');
        $this->addSql('ALTER TABLE abonnement CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE abonnement ADD CONSTRAINT FK_351268BB50EAE44 FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE abonnement RENAME INDEX fk_idclientab TO fk_id');
        $this->addSql('ALTER TABLE client DROP FOREIGN KEY fk_idclient');
        $this->addSql('ALTER TABLE client CHANGE id_client id_client INT NOT NULL, CHANGE permaban permaban TINYINT(1) DEFAULT NULL');
        $this->addSql('ALTER TABLE client ADD CONSTRAINT FK_C7440455E173B1B8 FOREIGN KEY (id_client) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY fk_idcoach');
        $this->addSql('ALTER TABLE coach CHANGE id_coach id_coach INT NOT NULL');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT FK_3F596DCCD1DC2CFC FOREIGN KEY (id_coach) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_idpub');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_idusercomment');
        $this->addSql('ALTER TABLE commentaire CHANGE idpub idpub INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCD731875A FOREIGN KEY (idclient_comment) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC434167E2 FOREIGN KEY (idpub) REFERENCES publication (id_pub)');
        $this->addSql('ALTER TABLE compte DROP FOREIGN KEY fk_compte');
        $this->addSql('ALTER TABLE compte CHANGE id_user id_user INT DEFAULT NULL');
        $this->addSql('ALTER TABLE compte ADD CONSTRAINT FK_CFF652606B3CA4B FOREIGN KEY (id_user) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE event CHANGE Description description VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE event ADD CONSTRAINT FK_3BAE0AA764C19C1 FOREIGN KEY (category) REFERENCES categorie_event (id_categ_event)');
        $this->addSql('ALTER TABLE participation DROP FOREIGN KEY fk_event');
        $this->addSql('ALTER TABLE participation DROP FOREIGN KEY fk_participation');
        $this->addSql('ALTER TABLE participation ADD CONSTRAINT FK_AB55E24F8B13D439 FOREIGN KEY (id_evenement) REFERENCES event (id_event)');
        $this->addSql('ALTER TABLE participation ADD CONSTRAINT FK_AB55E24FE173B1B8 FOREIGN KEY (id_client) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY fk_iduserpub');
        $this->addSql('ALTER TABLE publication CHANGE iduser iduser INT DEFAULT NULL, CHANGE contenu contenu VARCHAR(50) NOT NULL, CHANGE nbcomment nbcomment INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT FK_AF3C67795E5C27E9 FOREIGN KEY (iduser) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY fk_idclientreclam');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE6064048E0844B2 FOREIGN KEY (idclient_rec) REFERENCES utilisateur (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE abonnement DROP FOREIGN KEY FK_351268BB50EAE44');
        $this->addSql('ALTER TABLE abonnement CHANGE id_utilisateur id_utilisateur INT NOT NULL');
        $this->addSql('ALTER TABLE abonnement ADD CONSTRAINT fk_idclientab FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE abonnement RENAME INDEX fk_id TO fk_idclientab');
        $this->addSql('ALTER TABLE client DROP FOREIGN KEY FK_C7440455E173B1B8');
        $this->addSql('ALTER TABLE client CHANGE id_client id_client INT AUTO_INCREMENT NOT NULL, CHANGE permaban permaban TINYINT(1) DEFAULT \'0\'');
        $this->addSql('ALTER TABLE client ADD CONSTRAINT fk_idclient FOREIGN KEY (id_client) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE coach DROP FOREIGN KEY FK_3F596DCCD1DC2CFC');
        $this->addSql('ALTER TABLE coach CHANGE id_coach id_coach INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE coach ADD CONSTRAINT fk_idcoach FOREIGN KEY (id_coach) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCD731875A');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC434167E2');
        $this->addSql('ALTER TABLE commentaire CHANGE idpub idpub INT NOT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_idpub FOREIGN KEY (idpub) REFERENCES publication (id_pub) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_idusercomment FOREIGN KEY (idclient_comment) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE compte DROP FOREIGN KEY FK_CFF652606B3CA4B');
        $this->addSql('ALTER TABLE compte CHANGE id_user id_user INT NOT NULL');
        $this->addSql('ALTER TABLE compte ADD CONSTRAINT fk_compte FOREIGN KEY (id_user) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE event DROP FOREIGN KEY FK_3BAE0AA764C19C1');
        $this->addSql('ALTER TABLE event CHANGE description Description VARCHAR(255) CHARACTER SET latin1 DEFAULT NULL COLLATE `latin1_swedish_ci`');
        $this->addSql('ALTER TABLE participation DROP FOREIGN KEY FK_AB55E24F8B13D439');
        $this->addSql('ALTER TABLE participation DROP FOREIGN KEY FK_AB55E24FE173B1B8');
        $this->addSql('ALTER TABLE participation ADD CONSTRAINT fk_event FOREIGN KEY (id_evenement) REFERENCES event (id_event) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE participation ADD CONSTRAINT fk_participation FOREIGN KEY (id_client) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY FK_AF3C67795E5C27E9');
        $this->addSql('ALTER TABLE publication CHANGE iduser iduser INT DEFAULT 19, CHANGE nbcomment nbcomment INT DEFAULT 0, CHANGE contenu contenu VARCHAR(500) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT fk_iduserpub FOREIGN KEY (iduser) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE6064048E0844B2');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT fk_idclientreclam FOREIGN KEY (idclient_rec) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
