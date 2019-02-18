create table t_qrtz_locks
  (
    sched_name varchar(120) not null,
    lock_name  varchar(40) not null,
    primary key (sched_name,lock_name)
);
create table t_qrtz_fired_triggers
  (
    sched_name varchar(120) not null,
    entry_id varchar(95) not null,
    trigger_name varchar(200) not null,
    trigger_group varchar(200) not null,
    instance_name varchar(200) not null,
    fired_time bigint(13) not null,
    sched_time bigint(13) not null,
    priority integer not null,
    state varchar(16) not null,
    job_name varchar(200) null,
    job_group varchar(200) null,
    is_nonconcurrent varchar(1) null,
    requests_recovery varchar(1) null,
    primary key (sched_name,entry_id)
);

create table t_qrtz_paused_trigger_grps
(
    sched_name varchar(120) not null,
    trigger_group  varchar(200) not null,
    primary key (sched_name,trigger_group)
);

create table t_qrtz_cron_triggers
(
    sched_name varchar(120) not null,
    trigger_name varchar(200) not null,
    trigger_group varchar(200) not null,
    cron_expression varchar(200) not null,
    time_zone_id varchar(80),
    primary key (sched_name,trigger_name,trigger_group)
);

create table t_qrtz_blob_triggers
(
    sched_name varchar(120) not null,
    trigger_name varchar(200) not null,
    trigger_group varchar(200) not null,
    blob_data blob null,
    primary key (sched_name,trigger_name,trigger_group)
);

create table t_qrtz_calendars
(
    sched_name varchar(120) not null,
    calendar_name  varchar(200) not null,
    calendar blob not null,
    primary key (sched_name,calendar_name)
);

create table t_qrtz_scheduler_state
(
    sched_name varchar(120) not null,
    instance_name varchar(200) not null,
    last_checkin_time bigint(13) not null,
    checkin_interval bigint(13) not null,
    primary key (sched_name,instance_name)
);