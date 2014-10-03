//
//  ScheduleViewCell.h
//  SFIndieFest
//
//  Created by eric ringer on 10/1/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Film.h"
#include <EventKit/EventKit.h>
@interface ScheduleViewCell : UITableViewCell
@property (strong, nonatomic) IBOutlet UILabel *scheduleText;
@property (strong, nonatomic) IBOutlet UISwitch *scheduleSwitch;
@property (strong, nonatomic) IBOutlet UILabel *filmTime;
@property (strong, nonatomic) Film *film;
@property (strong, nonatomic) EKEventStore *eventStore;

@end
