Baccord
=======

Nástroj pro 3D rekonstrukci objektů z fotek z webů pro sdílení fotografií.

## Vize

Navrhnout a implementovat nástroj pro automatickou 3D rekonstrukci objektů z fotografií volně dostupných na internetu (primárně Flickr). 

Nástroj bude integrovat již hotové komponenty pro 3D rekonstrukci (structure from motion program Bundler a program pro rekonstrukci hustého mračna bodů - CMVS). Vstupem budou data z kolekce fotografií (vyhledávací výraz) a nebo již stažené fotografie. Nástroj automaticky připraví potřebné datové struktury pro následující kroky 3D rekonstrukce. Jednotlivé části rekonstrukčních programů budou konfigurovatelné a zaměnitelné (např. výběr SIFT detektoru).

### Screenshoty z hotové aplikace

![Vyhledávání](https://github.com/peruginni/Baccord/raw/master/docs/screenshot-search.png)
![Sfm zpracování](https://github.com/peruginni/Baccord/raw/master/docs/screenshot-sfm.png)

## Požadavky
* na logiku
  * stažení fotek na základě klíčového slova
  * připravit potřebné datové struktury pro 3D rekonstrukci
  * rekonstrukce 3D modelu z vybraných fotografií
  * zajistit konfigurovatelnost SfM, MVS programů
* na systém
  * naprogramovat v Javě
  * využít existujících programů Bundler, PMVS, CMVS
  * počítat s možností spuštění aplikace na Windows
* na uživatelské rozhraní
  * přehledné a jednoduché
  * bez prvků zbytečných pro aktuální činnost
  * průvodce fázemi procesu rekonstrukce
  * umožnit skok do libovolné fáze

## Případy užití

![Use case diagram](https://github.com/peruginni/Baccord/raw/master/docs/usecase.png)

## Návrh

### Architektura

![Diagram architektury](https://github.com/peruginni/Baccord/raw/master/docs/architecture.png)

### Diagram nasazení

![Diagram nasazení](https://github.com/peruginni/Baccord/raw/master/docs/deploy.png)

### Návrh tříd pro vyhledávání

![Diagram tříd pro vyhledávání](https://github.com/peruginni/Baccord/raw/master/docs/class-search.png)

### Uživatelské rozhraní

![Ukázka ui](https://github.com/peruginni/Baccord/raw/master/docs/wireframe.png)

* [Pick Photos](https://docs.google.com/drawings/edit?id=1w1b4LF6VXCQBOw1Fm1LYCboLIFrvTKV5D7RxaeUtbFE&hl=en)
* [SFM](https://docs.google.com/drawings/edit?id=1Xz06KYN_oarHnNsXns9ItN_q6TtNnTZqCIspHtoVpjk&hl=en)
* [MVS](https://docs.google.com/drawings/edit?id=1TJcAbM543UCngANtxX8j2BkskjUTYozKYxegJmr0xL0&hl=en)
* [Export](https://docs.google.com/drawings/edit?id=1-_J4P3UxCFMfDboVGP0BpeNNjxd7eYjqrOcDr2B6UqU&hl=en)
* [Settings](https://docs.google.com/drawings/edit?id=1ocrQo28QDiBfbUqCh-tb3WTBoChV1ApK5p_SeOh9xL8&hl=en)

