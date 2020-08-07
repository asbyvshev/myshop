ALTER TABLE `geek_shop`.`review`
ADD COLUMN `image` INT NULL AFTER `approved`,
ADD INDEX `fk_review_image_idx` (`image` ASC) VISIBLE;
;
ALTER TABLE `geek_shop`.`review`
ADD CONSTRAINT `fk_review_image`
  FOREIGN KEY (`image`)
  REFERENCES `geek_shop`.`image` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
