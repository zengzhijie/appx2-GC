-- ==================================================
-- 商品信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_goods;
CREATE TABLE godsi_goods (
	id             CHAR(32) NOT NULL COMMENT 'ID序列号',
	sto_id         CHAR(32) NOT NULL COMMENT '店铺ID',
	name           VARCHAR(120) NOT NULL COMMENT '名称',
	ctg_id         CHAR(32) COMMENT '类目id',
	min_pr         DECIMAL(7,2) COMMENT '最低售价',
	min_orig_pr    DECIMAL(7,2) COMMENT '最低原价',
	inv_typ		   CHAR(32) NOT NULL DEFAULT 'LIMITED' COMMENT '库存类型（UNLIMITED-无限制、LIMITED-有限制）',
	main_dig       VARCHAR(1020) COMMENT '主图',
	detail         TEXT(50000) COMMENT '详情',
	service		   TEXT(20000) COMMENT '售后服务',
	type		   CHAR(32) NOT NULL DEFAULT 'DEFAULT' COMMENT '商品类型（DEFAULT-默认或单独销售、BUNDLED-捆绑销售）',
	status         CHAR(32) NOT NULL DEFAULT 'APPLIED' COMMENT '状态（DEFAULT-默认或已上架、APPLIED -已下架、REMOVED-已移除。默认为：APPLIED-已下架）',
	is_rcd         TINYINT(1)	NOT NULL DEFAULT '0' COMMENT '是否推荐（1-是、0-不是）',
	crer_id        CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updr_id        CHAR(32) COMMENT '更新者用户ID号',
	upd_tim        DATETIME DEFAULT NULL COMMENT '更新时间',
	remark 		   VARCHAR(255) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='商品信息表' ENGINE=InnoDB;
CREATE INDEX idx_sto_id ON godsi_goods(sto_id, status);
CREATE INDEX idx_is_rcd ON godsi_goods(sto_id, is_rcd,status);

-- ==================================================
-- 商品属性名信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_gods_pro_nam;
CREATE TABLE godsi_gods_pro_nam (
	id          CHAR(32) NOT NULL COMMENT 'ID序列号',
	pro_nam_id  CHAR(32) NOT NULL COMMENT '属性名ID',
	gods_id     CHAR(32) NOT NULL COMMENT '商品ID',
	name        VARCHAR(40) COMMENT '名称',
	ctg_id 		CHAR(32) NOT NULL COMMENT '类目ID（子类目）',
	type 		CHAR(32) NOT NULL COMMENT '属性名类型（SYSTEM-系统、CUSTOMER-客户）',
	squence	    INT(3) NOT NULL COMMENT '排列顺序',
	is_srch     TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否搜索框（1-是、0-不是）',
	is_rad      TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否单选框（1-是、0-不是）',
	is_chk      TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否多选框（1-是、0-不是）',
	is_inp      TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否输入框（1-是、0-不是）',
	is_sel      TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否下拉框（1-是、0-不是）',
	is_ve       TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否文本编辑器（1-是、0-不是）',
	is_colr     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否颜色属性（1-是、0-不是）',
	is_req    	TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否必须属性（1-是、0-不是）',
	is_sal      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否销售属性（1-是、0-不是）',
	is_basc     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否基础属性（1-是、0-不是）',
	is_key      TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否关键属性（1-是、0-不是）',
	is_img	    TINYINT(1) DEFAULT '0' COMMENT '是否是图片属性（1-是、0-不是）',
	crer_id 	CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id)
) COMMENT='商品属性名信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_gods_id_pro_nam_id ON godsi_gods_pro_nam(gods_id, pro_nam_id);
CREATE INDEX idx_gods_id ON godsi_gods_pro_nam(gods_id);
CREATE INDEX idx_pro_nam_id ON godsi_gods_pro_nam(pro_nam_id);
CREATE INDEX idx_ctg_id ON godsi_gods_pro_nam(ctg_id);

-- ==================================================
-- 商品属性值信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_gods_pro_val;
CREATE TABLE godsi_gods_pro_val (
	id          	CHAR(32) NOT NULL COMMENT 'ID序列号',
	gods_pro_nam_id CHAR(32) COMMENT '商品属性名ID',
	pro_val_id      CHAR(32) COMMENT '属性值ID',
	pro_nam_id  	CHAR(32) COMMENT '属性名ID',
	gods_id     	CHAR(32) NOT NULL COMMENT '商品ID',
	pro_nam_typ 	CHAR(32) NOT NULL COMMENT '属性名类型（SYSTEM-系统、CUSTOMER-客户）',
	squence			INT(3) NOT NULL COMMENT '排列顺序',
	type 			CHAR(32) NOT NULL COMMENT '属性值类型（SYSTEM-系统、CUSTOMER-客户）',
	name        	TEXT(20000) COMMENT '名称',
	is_dflt_img	    TINYINT(1) DEFAULT '0' COMMENT '是否使用默认图片（1-是、0-不是）',
	img_url			VARCHAR(255) COMMENT '图片地址(用于图片属性)',
	crer_id 		CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim 		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	remark 		    VARCHAR(255) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='商品属性值信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_gods_id_pro_val_id ON godsi_gods_pro_val(gods_id, pro_val_id);
CREATE INDEX idx_gods_id ON godsi_gods_pro_val(gods_id);
CREATE INDEX idx_pro_nam_id ON godsi_gods_pro_val(pro_nam_id);
CREATE INDEX idx_pro_val_id ON godsi_gods_pro_val(pro_val_id);
CREATE INDEX idx_gods_id_pro_nam_id ON godsi_gods_pro_val(gods_id, pro_nam_id);

-- ==================================================
-- SKU信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_sku;
CREATE TABLE godsi_sku (
	id 			 CHAR(32) NOT NULL COMMENT 'ID序列号',
	gods_id		 CHAR(32) NOT NULL COMMENT '商品ID',
	inv_typ		 CHAR(32) NOT NULL DEFAULT 'LIMITED' COMMENT '库存类型（UNLIMITED-无限制、LIMITED-有限制）',
  	inventory	 INT(4) COMMENT '库存数量',
  	lock_inv	 INT(4) DEFAULT 0 COMMENT '锁定库存数',
  	sal_vol		 INT(4) COMMENT '起售量',
  	ori_pr		 DECIMAL(7,2) NOT NULL COMMENT '原价',
	price		 DECIMAL(7,2) NOT NULL COMMENT '售价',
	description	 VARCHAR(270) COMMENT '描述（属性名:属性值;...）',
	code		 VARCHAR(255) COMMENT '编码',
	barcode		 VARCHAR(255) COMMENT '条码',
	crer_id 	 CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim 	 TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	remark 		 VARCHAR(255) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='SKU信息表' ENGINE=InnoDB;
CREATE INDEX idx_gods_id ON godsi_sku(gods_id);

-- ==================================================
-- 分组信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_group;
CREATE TABLE godsi_group (
	id         CHAR(32) NOT NULL COMMENT 'ID序列号',
	sto_id     CHAR(32) NOT NULL COMMENT '店铺ID',
	name       VARCHAR(50) NOT NULL COMMENT '名称',
	par_id     CHAR(32) NOT NULL COMMENT '父分组id',
	is_par     TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否是父分组（1-是、0-不是）',
	squence    INT(3) NOT NULL COMMENT '排列序号',
	logo       VARCHAR(255) COMMENT '标识',
	intro      VARCHAR(2000) COMMENT '简介',
	status     CHAR(32) NOT NULL DEFAULT 'DEFAULT' COMMENT '状态（DEFAULT-默认或正常、DISABLED-已禁用）',
	url        CHAR(32) COMMENT '跳转地址（APPX专用）',
	gods_qty   INT(4) NOT NULL DEFAULT 0 COMMENT '商品数量',
	crer_id    CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updr_id    CHAR(32) COMMENT '更新者用户ID号',
	upd_tim    DATETIME DEFAULT NULL COMMENT '更新时间',
	remark 	   VARCHAR(255) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='分组信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_name ON godsi_group(sto_id, par_id, name);
CREATE INDEX idx_par_id ON godsi_group(par_id, sto_id);

-- ==================================================
-- 分组、商品关系表
-- ==================================================
DROP TABLE IF EXISTS godsi_gr_gods;
CREATE TABLE godsi_gr_gods (
  gr_id       CHAR(32) NOT NULL COMMENT 'ID序列号',
  gods_id     CHAR(32) NOT NULL COMMENT '商品ID',
  crer_id 	  CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  cre_tim 	  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (gr_id,gods_id)
) COMMENT='商品、分组关系表' ENGINE=InnoDB;
CREATE INDEX idx_gr_id ON godsi_gr_gods(gr_id);
CREATE INDEX idx_gods_id ON godsi_gr_gods(gods_id);

-- ==================================================
-- 商品、运费关系表
-- ==================================================
DROP TABLE IF EXISTS godsr_gods_frt;
CREATE TABLE godsr_gods_frt (
	gods_id	  CHAR(32) NOT NULL COMMENT 'ID序列号',
	frt_id    CHAR(32) NOT NULL COMMENT '运费模板ID',
	crer_id   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (gods_id)
) COMMENT='商品、运费关系表' ENGINE=InnoDB;
CREATE INDEX idx_frt_id ON godsr_gods_frt(frt_id);

-- ==================================================
-- 运费模板信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_freight;
CREATE TABLE godsi_freight (
	id          CHAR(32) NOT NULL COMMENT 'ID序列号',
	sto_id      CHAR(32) NOT NULL COMMENT '店铺ID',
	name        VARCHAR(100) NOT NULL COMMENT '名称',
	dlvr_add    VARCHAR(255) COMMENT '发货地址',
	dlvr_tim    VARCHAR(10) COMMENT '发货时间',
	payer       CHAR(32) NOT NULL COMMENT '支付方（BUYER-买家、SELLER-卖家）',
	pr_meth     CHAR(32) NOT NULL COMMENT '计价方式（NUMBER-件数、WEIGHT-重量、VOLUME-体积）',
	crer_id     CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updr_id     CHAR(32) COMMENT '更新者用户ID号',
	upd_tim     DATETIME COMMENT '更新时间',
	PRIMARY KEY (id)
) COMMENT='运费模板信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_nam ON godsi_freight(sto_id, name);
CREATE INDEX idx_sto_id ON godsi_freight(sto_id);

-- ==================================================
-- 物流方式信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_log_meth;
CREATE TABLE godsi_log_meth (
	id        CHAR(32) NOT NULL COMMENT 'ID序列号',
	frt_id    CHAR(32) NOT NULL COMMENT '运费模板ID',
	is_dflt   TINYINT(1) DEFAULT '0' NOT NULL COMMENT '是否默认',
	type      CHAR(32) NOT NULL COMMENT '类型（DELIVERY-快递）',
	strt_qty  DECIMAL(6,2) COMMENT '起始量',
	strt_pr   DECIMAL(7,2) COMMENT '起始价格',
	inc_qty   DECIMAL(6,2) COMMENT '增量',
	inc_pr    DECIMAL(7,2) COMMENT '增加价格',
	crer_id   CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id)
) COMMENT='物流方式信息表' ENGINE=InnoDB;
CREATE INDEX idx_frt_id ON godsi_log_meth(frt_id);

-- ==================================================
-- 城市信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_city;
CREATE TABLE godsi_city (
  	id          CHAR(32) NOT NULL COMMENT 'ID序列号',
  	log_meth_id CHAR(32) NOT NULL COMMENT '物流方式ID',
  	cty_id      CHAR(32) NOT NULL COMMENT '城市ID',
  	par_id      CHAR(32) COMMENT '父级ID',
  	name        VARCHAR(50) NOT NULL COMMENT '名称',
  	crer_id     CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  	cre_tim     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  	PRIMARY KEY  (id)
) COMMENT='城市信息' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_nam ON godsi_city(log_meth_id, name);
CREATE INDEX idx_log_meth_id ON godsi_city(log_meth_id);

-- ==================================================
-- 运费参数表
-- ==================================================
DROP TABLE IF EXISTS godsi_frt_par;
CREATE TABLE godsi_frt_par (
	gods_id  CHAR(32) NOT NULL COMMENT '商品ID',
	type     CHAR(32) NOT NULL COMMENT '运费类型（FIXED-固定、NOFIXED-不固定）',
	price    DECIMAL(6,2) COMMENT '价格(固定运费需要填写)',
	frt_id   CHAR(32) COMMENT '运费模板ID',
	amount   VARCHAR(255) COMMENT '量（体积或重量）',
	crer_id  CHAR(32) NOT NULL COMMENT '创建者用户ID号',
  	cre_tim  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  	updr_id  CHAR(32) DEFAULT NULL COMMENT '更新者用户ID号',
  	upd_tim  DATETIME DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (gods_id)
) COMMENT='运费参数表' ENGINE=InnoDB;
CREATE INDEX idx_frt_id ON godsi_frt_par(frt_id);

-- ==================================================
-- 库存操作明细表
-- ==================================================
DROP TABLE IF EXISTS godsi_inv_op_dtl;
CREATE TABLE godsi_inv_op_dtl (
	ord_id         CHAR(32) NOT NULL COMMENT 'ID序列号',
	sku_id         CHAR(32) NOT NULL COMMENT '店铺ID',
	inventory      INT(4) COMMENT '操作库存数',
	status         CHAR(32) NOT NULL DEFAULT 'LOCKED' COMMENT '状态（LOCKED-已锁定、RELEASED-已释放、RETURNED-已扣减）',
	crer_id        CHAR(32) NOT NULL COMMENT '创建者用户ID号',
	cre_tim        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updr_id        CHAR(32) COMMENT '更新者用户ID号',
	upd_tim        DATETIME DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (ord_id, sku_id)
) COMMENT='库存操作明细表' ENGINE=InnoDB;


-- ==================================================
-- 小程序应用信息表
-- ==================================================
DROP TABLE IF EXISTS godsi_app;
CREATE TABLE godsi_app (
	gods_id        CHAR(32) NOT NULL COMMENT '商品ID',
	tem_id         CHAR(32) NOT NULL COMMENT '小程序模板ID',
	app_code       VARCHAR(255) NOT NULL COMMENT '小程序码',
	image          VARCHAR(255) NOT NULL COMMENT '首页配图',
	PRIMARY KEY (gods_id)
) COMMENT='小程序应用信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_tem_id ON godsi_app(gods_id, tem_id);
