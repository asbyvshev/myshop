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

DROP TABLE IF EXISTS `geek_shop`.`purchase_product`;

--CREATE TABLE `geek_shop`.`purchase_product` (
--    purchase UUID NOT NULL CONSTRAINT FK_purchase_product_purchase REFERENCES purchase,
--    product UUID NOT NULL CONSTRAINT FK_purchase_product_product REFERENCES product
--);