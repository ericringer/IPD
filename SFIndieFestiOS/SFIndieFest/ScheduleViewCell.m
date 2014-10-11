//
//  ScheduleViewCell.m
//  SFIndieFest
//
//  Created by eric ringer on 10/1/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "ScheduleViewCell.h"

@implementation ScheduleViewCell
@synthesize scheduleSwitch,scheduleText,filmTime,film,eventStore;
BOOL didGrant;
- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}
- (IBAction)btnReminder:(id)sender {
    NSLog(@"%@ %@ Siwtched: %hhd",[film filmTitle],[film convertedDate],[scheduleSwitch isOn]);
    if([scheduleSwitch isOn]){
        [self addReminder];
    }else{
        [self removeReminder];
    }
}
-(void)addReminder{
    EKReminder *reminder = [EKReminder
                            reminderWithEventStore:self.eventStore];
    
    reminder.title = [film filmTitle];
    
    reminder.calendar = [eventStore defaultCalendarForNewReminders];
    
    NSDate *date = [film convertedDate];
    
    EKAlarm *alarm = [EKAlarm alarmWithAbsoluteDate:date];
    
    [reminder addAlarm:alarm];
    
    
    NSError *error = nil;
    
    [eventStore saveReminder:reminder commit:YES error:&error];
    
    if (error){
        NSLog(@"error = %@", error);
    }else{
        [film setReminder:reminder];
    }
}
-(void)removeReminder{
    if([film reminder]!=nil){
        NSError *error = nil;
        [eventStore removeReminder:[film reminder] commit:YES error:&error];
        if (error)
            NSLog(@"error = %@", error);
    }
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
