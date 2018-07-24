-- ==================================================
-- 类目信息表
-- ==================================================
DROP TABLE IF EXISTS ctgi_category;
CREATE TABLE ctgi_category (
  id 			  CHAR(32) NOT NULL COMMENT 'ID序列号',
  name		  VARCHAR(20) NOT NULL COMMENT '名称',
  sequence	INT(3) NOT NULL COMMENT '排列顺序',
  par_id	  CHAR(32) NOT NULL DEFAULT '0' COMMENT '父类目ID',
  is_par   TINYINT(1)	DEFAULT '0' NOT NULL COMMENT '是否为父级类目',
  crer_id 	CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (id)
) COMMENT='类目信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_ctg_nam ON ctgi_category(par_id,name);
CREATE INDEX idx_par_id  USING BTREE ON ctgi_category (par_id);

-- ==================================================
-- 系统属性名信息表
-- ==================================================
DROP TABLE IF EXISTS ctgi_sys_pro_nam;
CREATE TABLE ctgi_sys_pro_nam (
  id          CHAR(32) NOT NULL COMMENT 'ID序列号',
  name        VARCHAR(40) NOT NULL COMMENT '名称',
  ctg_id 			 CHAR(32) NOT NULL COMMENT '类目ID（顶级类目）',
  is_srch     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否搜索框（1-是、0-不是）',
  is_rad      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否单选框（1-是、0-不是）',
	is_chk      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否多选框（1-是、0-不是）',
	is_inp      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否输入框（1-是、0-不是）',
	is_sel      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否下拉框（1-是、0-不是）',
	is_ve       TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否文本编辑器（1-是、0-不是）',
	is_colr     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否颜色属性（1-是、0-不是）',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (id)
) COMMENT='系统属性名信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_pro_nam ON ctgi_sys_pro_nam(ctg_id,name);
CREATE INDEX idx_ctg_id ON ctgi_sys_pro_nam(ctg_id);

-- ==================================================
-- 系统属性值信息表
-- ==================================================
DROP TABLE IF EXISTS ctgi_sys_pro_val;
CREATE TABLE ctgi_sys_pro_val (
  id          CHAR(32) NOT NULL COMMENT 'ID序列号',
  pro_nam_id  CHAR(32) NOT NULL COMMENT '属性名ID（系统属性名）',
  name        TEXT(20000) NOT NULL COMMENT '名称',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(128) COMMENT '备注',
  PRIMARY KEY (id)
) COMMENT='系统属性值信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_sys_pro_val ON ctgi_sys_pro_val(pro_nam_id,name);
CREATE INDEX idx_sys_pro_name_id ON ctgi_sys_pro_val(pro_nam_id);

-- ==================================================
-- 类目、系统属性名关系表
-- ==================================================
DROP TABLE IF EXISTS ctgr_ctg_sys_pro_nam;
CREATE TABLE ctgr_ctg_sys_pro_nam (
  ctg_id      CHAR(32) NOT NULL COMMENT '类目ID(子类目)',
  pro_nam_id  CHAR(32) NOT NULL COMMENT '属性名ID',
  is_req    	 TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否必须属性（1-是、0-不是）',
  is_sal      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否销售属性（1-是、0-不是）',
  is_basc     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否基础属性（1-是、0-不是）',
  is_key      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否关键属性（1-是、0-不是）',
  sequence	   INT(3) NOT NULL COMMENT '排列顺序',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (ctg_id,pro_nam_id)
) COMMENT='类目、系统属性名关系表' ENGINE=InnoDB;
CREATE INDEX idx_ctg_id ON ctgr_ctg_sys_pro_nam(ctg_id);

-- ==================================================
-- 类目、系统属性值关系表
-- ==================================================
DROP TABLE IF EXISTS ctgr_ctg_sys_pro_val;
CREATE TABLE ctgr_ctg_sys_pro_val (
  ctg_id      CHAR(32) NOT NULL COMMENT '类目ID(子类目)',
  pro_nam_id  CHAR(32) NOT NULL COMMENT '属性名ID',
  pro_val_id  CHAR(32) NOT NULL COMMENT '属性值ID',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (ctg_id,pro_val_id)
) COMMENT='类目、系统属性值关系表' ENGINE=InnoDB;
CREATE INDEX idx_sys_pro_nam_id ON ctgr_ctg_sys_pro_val(ctg_id,pro_nam_id);

-- ==================================================
-- 客户属性名信息表
-- ==================================================
DROP TABLE IF EXISTS ctgi_cust_pro_nam;
CREATE TABLE ctgi_cust_pro_nam (
  id          CHAR(32) NOT NULL COMMENT 'ID序列号',
  sto_id      CHAR(32) NOT NULL COMMENT '店铺ID',
  name        VARCHAR(40) NOT NULL COMMENT '名称',
  ctg_id 			 CHAR(32) NOT NULL COMMENT '类目ID（子类目）',
  sequence	   INT(3) NOT NULL COMMENT '排列顺序',
  is_srch     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否搜索框（1-是、0-不是）',
  is_rad      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否单选框（1-是、0-不是）',
	is_chk      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否多选框（1-是、0-不是）',
	is_inp      TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否输入框（1-是、0-不是）',
	is_sel      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否下拉框（1-是、0-不是）',
	is_ve       TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否文本编辑器（1-是、0-不是）',
	is_colr     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否颜色属性（1-是、0-不是）',
	is_req    	 TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否必须属性（1-是、0-不是）',
  is_sal      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否销售属性（1-是、0-不是）',
  is_basc     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否基础属性（1-是、0-不是）',
  is_key      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否关键属性（1-是、0-不是）',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (id)
) COMMENT='客户属性名信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_pro_nam ON ctgi_cust_pro_nam(sto_id,ctg_id,name);
CREATE INDEX idx_ctg_id ON ctgi_cust_pro_nam(sto_id,ctg_id);
CREATE INDEX idx_sto_id ON ctgi_cust_pro_nam(sto_id);

-- ==================================================
-- 客户属性值信息表
-- ==================================================
DROP TABLE IF EXISTS ctgi_cust_pro_val;
CREATE TABLE ctgi_cust_pro_val (
  id          CHAR(32) NOT NULL COMMENT 'ID序列号',
  sto_id      CHAR(32) NOT NULL COMMENT '店铺ID',
  ctg_id 			 CHAR(32) NOT NULL COMMENT '类目ID（子类目）',
  pro_nam_typ CHAR(32) NOT NULL COMMENT '属性名类型（SYSTEM-系统、CUSTOMER-客户）',
  pro_nam_id  CHAR(32) NOT NULL COMMENT '属性名ID（系统/客户属性名）',
  name        TEXT(20000) NOT NULL COMMENT '名称',
  crer_id 	   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updr_id 	   CHAR(32) COMMENT '更新者用户ID号',
  upd_tim 	   DATETIME COMMENT '更新时间',
  remark 		   VARCHAR(2000) COMMENT '备注',
  PRIMARY KEY (id)
) COMMENT='客户属性值信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_cust_pro_val ON ctgi_cust_pro_val(sto_id,ctg_id,pro_nam_id,name);
CREATE INDEX idx_pro_nam_id ON ctgi_cust_pro_val(sto_id,ctg_id,pro_nam_id);
