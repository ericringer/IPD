//
//  ScheduleViewCell.m
//  SFIndieFest
//
//  Created by eric ringer on 10/1/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "ScheduleViewCell.h"

@implementation ScheduleViewCell
@synthesize scheduleText,filmTime,film;

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
