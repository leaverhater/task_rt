CREATE TABLE BRANCH_REF
(  "ID" NUMBER(22,0) NOT NULL ,		--	Идентификатор записи в таблице
	"PARENT_ID" NUMBER(22,0),	-- Идентификатор родительского подразделения
	"NAME" VARCHAR2(240) NOT NULL,	--	Название подразделения
	"CODE" VARCHAR2(40) NOT NULL,	--	Код подразделения
	"CHANGE_TIME" DATE,	-- Дата изменения записи в таблице
	CONSTRAINT BRANCH_PK PRIMARY KEY (ID)
)